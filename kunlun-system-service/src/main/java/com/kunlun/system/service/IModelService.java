package com.kunlun.system.service;

import com.kunlun.system.model.ModelModel;
import com.scmp.common.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IModelService {
    public Page getAllProcess(ModelModel model, int currentPage, int pageSize) throws Exception;

    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String type) throws Exception;

    public boolean batchDeleteModel(String[] processIds) throws Exception;
}
