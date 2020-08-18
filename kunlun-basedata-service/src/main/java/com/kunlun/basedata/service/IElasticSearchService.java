package com.kunlun.basedata.service;

import com.kunlun.basedata.model.ServiceTraceModel;

import java.util.List;

public interface IElasticSearchService {

    public List<ServiceTraceModel> queryServiceInvokes() throws Exception;
}
