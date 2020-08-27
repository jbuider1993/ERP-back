package com.kunlun.basedata.service;

import com.kunlun.basedata.model.HomeCountModel;
import com.kunlun.basedata.model.vo.MemoryDiskVo;

public interface IHomeService {
    public HomeCountModel getUserCount() throws Exception;

    public MemoryDiskVo getDiskInfo() throws Exception;
}
