package com.kunlun.basedata.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kunlun.basedata.model.ServiceInvokeModel;
import com.kunlun.basedata.model.ServiceTraceModel;
import com.kunlun.basedata.service.IElasticSearchService;
import com.kunlun.basedata.utils.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ElasticSearchService implements IElasticSearchService {

    private static Logger log = LogManager.getLogger();

    @Autowired
    private RestHighLevelClient highLevelClient;

    @Override
    public List<ServiceInvokeModel> queryServiceInvokes() throws Exception {
        // 范围查询策略
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("duration").gte(14752);

        // 创建查询源构造器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);

        // 设置分页
        sourceBuilder.from(0);
        sourceBuilder.size(10000);

        // 查询所有index
        GetAliasesRequest request = new GetAliasesRequest();
        GetAliasesResponse getAliasesResponse =  highLevelClient.indices().getAlias(request,RequestOptions.DEFAULT);
        Map<String, Set<AliasMetaData>> map = getAliasesResponse.getAliases();
        Set<String> indexNameSet = map.keySet().stream().filter(obj -> obj.contains("zipkin-es")).collect(Collectors.toSet());
        Map<String, ServiceInvokeModel> resultMap = new HashMap<>();
        for (String indexName : indexNameSet) {
            // 创建查询请求对象，将查询对象配置到其中
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(sourceBuilder);

            // 执行查询，然后处理响应结果
            SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().totalHits > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    JSONObject jsonObject = JSONObject.parseObject(hit.getSourceAsString());
                    ServiceTraceModel traceModel = JSON.parseObject(String.valueOf(jsonObject), ServiceTraceModel.class);

                    JSONObject localEndpointObject = jsonObject.getJSONObject("localEndpoint");
                    String serviceName = localEndpointObject.getString("serviceName");
                    if (resultMap.containsKey(serviceName)) {
                        ServiceInvokeModel model = resultMap.get(serviceName);
                        long count = model.getCount();
                        count++;
                        model.setCount(count);
                    } else {
                        ServiceInvokeModel invokeModel = new ServiceInvokeModel();
                        invokeModel.setId(CommonUtil.generateUUID());
                        invokeModel.setRequestType(traceModel.getType());
                        invokeModel.setServiceName(serviceName);
                        invokeModel.setIpv4(localEndpointObject.getString("ipv4"));

                        JSONObject remoteEndpointObject = jsonObject.getJSONObject("remoteEndpoint");
                        if (!ObjectUtils.isEmpty(remoteEndpointObject)) {
                            invokeModel.setPort(remoteEndpointObject.getString("port"));
                        }

                        JSONObject tagsObject = jsonObject.getJSONObject("tags");
                        if (!ObjectUtils.isEmpty(tagsObject)) {
                            invokeModel.setClz(tagsObject.getString("mvc.controller.class"));
                            invokeModel.setStatus(tagsObject.getString("http.status_cod"));
                            invokeModel.setPath(tagsObject.getString("http.path"));
                        }
                        invokeModel.setCount(0);
                        resultMap.put(invokeModel.getServiceName(), invokeModel);
                    }
                }
            }
        }
        return resultMap.entrySet().stream().map(obj -> obj.getValue()).collect(Collectors.toList());
    }
}
