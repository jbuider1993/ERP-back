package com.scmp.base.controller;

import com.scmp.base.service.ICodeService;
import com.scmp.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/code")
public class CodeController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private ICodeService codeService;

    @RequestMapping(value="/getAuthCode", method= RequestMethod.GET)
    public Object getAuthCode() {
        try {
            return ResponseUtil.successResponse(codeService.getAuthCode());
        } catch (Exception e) {
            log.error("CodeController getAuthCode Error: ", e);
            return ResponseUtil.failedResponse("生成验证码失败！", e.getMessage());
        }
    }
}
