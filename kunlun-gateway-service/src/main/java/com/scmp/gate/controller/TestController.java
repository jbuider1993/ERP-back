package com.scmp.gate.controller;

import com.scmp.common.utils.ResponseUtil;
import com.scmp.gate.amqp.OperatorLogSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private OperatorLogSender operatorLogSender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Object send(HttpServletRequest request, String userName, String password, String code) {
        try {
            operatorLogSender.sendOperatorLog("0123456789");
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            log.error("TestController send Error: ", e);
            return ResponseUtil.failedResponse("发送失败！", e.getMessage());
        }
    }
}
