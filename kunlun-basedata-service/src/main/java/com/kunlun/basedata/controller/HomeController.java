package com.kunlun.basedata.controller;

import com.kunlun.basedata.service.IHomeService;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IHomeService homeService;

    @RequestMapping(value="/getUserCount", method= RequestMethod.GET)
    public Object getUserCount() {
        try {
            return ResponseUtil.successResponse(homeService.getUserCount());
        } catch (Exception e) {
            log.error("HomeController getUserCount Error: ", e);
            return ResponseUtil.failedResponse("用户数获取失败！", e.getMessage());
        }
    }
}
