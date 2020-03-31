package com.kunlun.gateway.controller;

import com.kunlun.gateway.model.TokenModel;
import com.scmp.common.utils.ResponseUtil;
import com.kunlun.gateway.service.IShiroService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/shiro")
public class ShiroController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IShiroService shiroService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(HttpServletRequest request, String userName, String password, String code) {
        try {
            // url过滤及认证
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);

            // 处理登录用户
            TokenModel tokenModel = shiroService.handleLogin(userName, password);
            return ResponseUtil.successResponse(tokenModel);
        } catch (Exception e) {
            log.error("ShiroController login Error: ", e);
            return ResponseUtil.failedResponse("登录失败，用户名或密码错误！", e.getMessage());
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout(HttpServletRequest request, String userName, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();

            // 处理用户注销登录
            shiroService.handleLogout(request, userName);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("ShiroController logout Error: ", e);
            return ResponseUtil.failedResponse("退出系统失败！", e.getMessage());
        }
    }
}
