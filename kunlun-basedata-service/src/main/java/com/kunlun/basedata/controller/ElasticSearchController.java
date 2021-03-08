package com.kunlun.basedata.controller;

import com.kunlun.basedata.model.ServiceInvokeModel;
import com.kunlun.basedata.model.ServiceTraceModel;
import com.kunlun.basedata.service.IElasticSearchService;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elasticSearch")
public class ElasticSearchController {

    private static Logger log = LogManager.getLogger();

    @Autowired
    private IElasticSearchService elasticSearchService;

    @RequestMapping("/queryServiceInvokes")
    public Object queryServiceInvokes() {
        try {
            List<ServiceInvokeModel> invokeModels = elasticSearchService.queryServiceInvokes();
            return ResponseUtil.successResponse(invokeModels);
        } catch (Exception e) {
            log.error("ElasticSearchController queryServiceInvokes error", e);
            return ResponseUtil.failedResponse("ElasticSearchController queryServiceInvokes error", e.getLocalizedMessage());
        }
    }
}
