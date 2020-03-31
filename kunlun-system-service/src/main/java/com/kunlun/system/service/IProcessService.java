package com.kunlun.system.service;

import com.kunlun.system.model.ProcessModel;
import com.scmp.common.model.Page;

public interface IProcessService {
    public Page getAllProcess(ProcessModel model, int currentPage, int pageSize) throws Exception;
}
