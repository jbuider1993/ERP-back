package com.kunlun.system.service;

import com.kunlun.common.model.OperatorLogModel;
import com.kunlun.common.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IOperateLogService {
    public Page getAllOperateLog(OperatorLogModel logModel, int currentPage, int pageSize) throws Exception;

    public int getOperateLogCount(Map<String, Object> queryMap) throws Exception;

    public void insertOperateLog(OperatorLogModel logModel) throws Exception;

    public void updateOperateLog(OperatorLogModel logModel) throws Exception;

    public void exportOperateLog(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
