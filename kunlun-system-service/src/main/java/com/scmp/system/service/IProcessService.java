package com.scmp.system.service;

import com.scmp.common.model.Page;
import com.scmp.system.model.ProcessModel;

public interface IProcessService {
    public Page getAllProcess(ProcessModel model, int currentPage, int pageSize) throws Exception;
}
