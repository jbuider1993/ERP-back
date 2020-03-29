package com.scmp.system.controller;

import com.scmp.common.model.OperatorLogModel;
import com.scmp.common.model.Page;
import com.scmp.common.utils.ResponseUtil;
import com.scmp.system.service.IOperateLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志Controller
 */
@RestController
@RequestMapping(value = "/log")
public class OperatorLogController {
    private Logger log = LogManager.getLogger();

    @Autowired
    private IOperateLogService operateLogService;

    @RequestMapping(value = "/getLogList", method = RequestMethod.GET)
    public Object getLogList(OperatorLogModel logModel, int currentPage, int pageSize) {
        try {
            Page logList = operateLogService.getAllOperateLog(logModel, currentPage, pageSize);
            return ResponseUtil.successResponse(logList);
        } catch (Exception e) {
            log.error("OperatorLogController getLogList Error: ", e);
            return ResponseUtil.failedResponse("查询操作日志失败！", e.getMessage());
        }
    }
}
