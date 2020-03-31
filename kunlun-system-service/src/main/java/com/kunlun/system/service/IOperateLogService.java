package com.kunlun.system.service;

import com.scmp.common.model.OperatorLogModel;
import com.scmp.common.model.Page;

import java.util.Map;

public interface IOperateLogService {
    public Page getAllOperateLog(OperatorLogModel logModel, int currentPage, int pageSize) throws Exception;

    public int getOperateLogCount(Map<String, Object> queryMap) throws Exception;

    public void insertOperateLog(OperatorLogModel logModel) throws Exception;

    public void updateOperateLog(OperatorLogModel logModel) throws Exception;
}
