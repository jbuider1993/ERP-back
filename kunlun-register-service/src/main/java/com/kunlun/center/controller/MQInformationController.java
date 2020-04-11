package com.kunlun.center.controller;

import com.kunlun.center.service.IMQInformationService;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mq")
public class MQInformationController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IMQInformationService mqInformationService;

    @RequestMapping(value = "/getMessages", method = RequestMethod.GET)
    public Object getMessages() {
        try {
            return ResponseUtil.successResponse(mqInformationService.getMessages());
        } catch (Exception e) {
            log.error("MQInformationController getQueues Error: ", e);
            return ResponseUtil.failedResponse("获取所有MQ队列及交换器失败！", e.getMessage());
        }
    }
}
