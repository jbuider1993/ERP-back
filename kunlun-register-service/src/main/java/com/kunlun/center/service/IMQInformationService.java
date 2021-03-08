package com.kunlun.center.service;

import com.kunlun.center.model.MQInfoVo;

import java.util.List;

public interface IMQInformationService {

    public List<MQInfoVo> getMessages() throws Exception;
}
