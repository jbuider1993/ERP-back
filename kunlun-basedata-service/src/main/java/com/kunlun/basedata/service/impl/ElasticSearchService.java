package com.kunlun.basedata.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kunlun.basedata.model.ServiceTraceModel;
import com.kunlun.basedata.service.IElasticSearchService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ElasticSearchService implements IElasticSearchService {

    private static Logger log = LogManager.getLogger();

    @Autowired
    private RestHighLevelClient highLevelClient;

    @Override
    public List<ServiceTraceModel> queryServiceInvokes() throws Exception {
        // 查询所有index
        GetAliasesRequest request = new GetAliasesRequest();
        GetAliasesResponse getAliasesResponse =  highLevelClient.indices().getAlias(request,RequestOptions.DEFAULT);
        Map<String, Set<AliasMetaData>> map = getAliasesResponse.getAliases();
        String indexName = map.keySet().stream().filter(obj -> obj.contains("zipkin-es")).sorted((x, y) -> - x.compareTo(y)).findFirst().get();

        // 范围查询策略
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("duration").gte(14752);

        // 创建查询源构造器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);

        // 设置分页
        sourceBuilder.from(0);
        sourceBuilder.size(10000);

        // 创建查询请求对象，将查询对象配置到其中
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(sourceBuilder);

        // 执行查询，然后处理响应结果
        List<ServiceTraceModel> results = new ArrayList<>();
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().totalHits > 0) {
            SearchHits hits = searchResponse.getHits();
            System.out.println("totalHits ===>>> " + hits.totalHits);
            for (SearchHit hit : hits) {
                JSONObject jsonObject = JSONObject.parseObject(hit.getSourceAsString());
                ServiceTraceModel traceModel = JSON.parseObject(String.valueOf(jsonObject), ServiceTraceModel.class);

                JSONObject localEndpointObject = jsonObject.getJSONObject("localEndpoint");
                traceModel.setIpv4(localEndpointObject.getString("ipv4"));
                traceModel.setLocalServceName(localEndpointObject.getString("serviceName"));

                JSONObject remoteEndpointObject = jsonObject.getJSONObject("remoteEndpoint");
                if (!ObjectUtils.isEmpty(remoteEndpointObject)) {
                    traceModel.setPort(remoteEndpointObject.getString("port"));
                    traceModel.setRemoteServceName(remoteEndpointObject.getString("serviceName"));
                }

                JSONObject tagsObject = jsonObject.getJSONObject("tags");
                if (!ObjectUtils.isEmpty(tagsObject)) {
                    traceModel.setClz(tagsObject.getString("mvc.controller.class"));
                    traceModel.setMethod(tagsObject.getString("mvc.controller.method"));
                    traceModel.setPath(tagsObject.getString("http.path"));
                    traceModel.setType(tagsObject.getString("http.method"));
                    traceModel.setStatusCode(tagsObject.getString("http.status_cod"));
                    traceModel.setError(tagsObject.getString("error"));
                }

                results.add(traceModel);
            }
        }
        return results;
    }
}
