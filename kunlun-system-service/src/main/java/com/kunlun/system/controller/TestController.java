package com.kunlun.system.controller;

import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.service.IBasedataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IBasedataService basedataService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Object send(String userName, String password, String code) {
        try {
            basedataService.set("12345", "12345", 30000, 0);
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            log.error("TestController send Error: ", e);
            return ResponseUtil.failedResponse("发送失败！", e.getMessage());
        }
    }
}
