package com.kunlun.basedata.service;

import com.kunlun.basedata.model.HomeCountModel;
import com.kunlun.common.model.SystemDataModel;

public interface IHomeService {
    public HomeCountModel getUserCount() throws Exception;

    public SystemDataModel collectMonitor() throws Exception;
}
