package com.kunlun.system.service;

import com.kunlun.common.model.Page;
import com.kunlun.system.model.ProcessModel;
import com.kunlun.system.model.activiti.TaskInstModel;

public interface IProcessService {
    public Page getAllProcess(ProcessModel model, int currentPage, int pageSize) throws Exception;

    public TaskInstModel getCurrentProcessNode(String procDefId, String procInstId) throws Exception;
}
