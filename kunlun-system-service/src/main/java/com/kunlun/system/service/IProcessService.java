package com.kunlun.system.service;

import com.kunlun.common.model.Page;
import com.kunlun.system.model.ProcessModel;

public interface IProcessService {
    public Page getAllProcess(ProcessModel model, int currentPage, int pageSize) throws Exception;
}
