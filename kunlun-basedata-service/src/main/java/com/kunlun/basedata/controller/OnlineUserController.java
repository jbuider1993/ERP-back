package com.kunlun.basedata.controller;

import com.kunlun.basedata.model.OnlineUserModel;
import com.kunlun.basedata.service.IOnlineUserService;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/onlineUser")
public class OnlineUserController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IOnlineUserService onlineUserService;

    @RequestMapping(value = "/getAllOnlineUser", method = RequestMethod.GET)
    public Object getAllOnlineUser(OnlineUserModel onlineUserModel, int currentPage, int pageSize) {
        try {
            Page users = onlineUserService.getAllOnlineUser(onlineUserModel, currentPage, pageSize);
            return ResponseUtil.successResponse(users);
        } catch (Exception e) {
            log.error("OnlineUserController getAllOnlineUser Error: ", e);
            return ResponseUtil.failedResponse("查询所有在线用户失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addOnlineUser", method = RequestMethod.POST)
    public Object addOnlineUser(HttpServletRequest request, String userName) {
        try {
            OnlineUserModel onlineUserModel = onlineUserService.addOnlineUser(request, userName);
            return ResponseUtil.successResponse(onlineUserModel);
        } catch (Exception e) {
            log.error("OnlineUserController addOnlineUser Error: ", e);
            return ResponseUtil.failedResponse("新增在线用户失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateOnlineUser", method = RequestMethod.POST)
    public Object updateOnlineUser(String userName, String loginTime) {
        try {
            onlineUserService.updateOnlineUser(userName, loginTime);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("OnlineUserController updateOnlineUser Error: ", e);
            return ResponseUtil.failedResponse("更新所有在线用户失败！", e.getMessage());
        }
    }
}
