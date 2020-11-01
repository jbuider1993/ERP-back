package com.kunlun.center.controller;

import com.kunlun.common.model.SystemDataModel;
import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.common.utils.SystemMonitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class CommonController {

    private Logger log = LogManager.getLogger();

    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    public Object collectMonitor() {
        try {
            SystemDataModel systemDataModel = SystemMonitor.collect();
            return ResponseUtil.successResponse(systemDataModel);
        } catch (Exception e) {
            log.error("收集系统信息失败", e);
            return ResponseUtil.failedResponse("收集系统信息成功", e.getMessage());
        }
    }
}
