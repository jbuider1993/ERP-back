package com.kunlun.system.controller;

import com.kunlun.common.model.OperatorLogModel;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.service.IOperateLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/exportOperateLog", method = RequestMethod.GET)
    public Object exportOperateLog(HttpServletRequest request, HttpServletResponse response) {
        try {
            operateLogService.exportOperateLog(request, response);
        } catch (Exception e) {
            log.error("OperatorLogController exportOperateLog Error: ", e);
            return ResponseUtil.failedResponse("导出操作日志失败！", e.getMessage());
        }
        return null;
    }
}
