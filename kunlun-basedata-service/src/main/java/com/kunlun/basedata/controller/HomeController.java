package com.kunlun.basedata.controller;

import com.kunlun.basedata.service.IHomeService;
import com.kunlun.basedata.service.IRedisService;
import com.kunlun.common.model.SystemDataModel;
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

    @Autowired
    private IRedisService redisService;

    @RequestMapping(value="/getUserCount", method= RequestMethod.GET)
    public Object getUserCount() {
        try {
            return ResponseUtil.successResponse(homeService.getUserCount());
        } catch (Exception e) {
            log.error("HomeController getUserCount Error: ", e);
            return ResponseUtil.failedResponse("用户数获取失败！", e.getMessage());
        }
    }

    @RequestMapping(value="/getRedisInfo", method= RequestMethod.GET)
    public Object getRedisInfo() {
        try {
            return ResponseUtil.successResponse(redisService.getRedisInfo());
        } catch (Exception e) {
            log.error("HomeController getUserCount Error: ", e);
            return ResponseUtil.failedResponse("系统磁盘及内存使用率获取失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public Object collectMonitor() {
        try {
            SystemDataModel systemDataModel = homeService.collectMonitor();
            return ResponseUtil.successResponse(systemDataModel);
        } catch (Exception e) {
            log.error("收集系统信息失败", e);
            return ResponseUtil.failedResponse("收集系统信息成功", e.getMessage());
        }
    }
}
