package com.scmp.system.service;

import com.scmp.common.model.Page;
import com.scmp.system.model.ModelModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IModelService {
    public Page getAllProcess(ModelModel model, int currentPage, int pageSize) throws Exception;

    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String type) throws Exception;

    public boolean batchDeleteModel(String[] processIds) throws Exception;
}
