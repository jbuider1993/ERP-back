package com.kunlun.basedata.controller;

import com.kunlun.basedata.model.UserModel;
import com.kunlun.basedata.service.IUserService;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public Object getAllUser(UserModel userMode, int currentPage, int pageSize) {
        try {
            Page users = userService.getAllUser(userMode, currentPage, pageSize);
            return ResponseUtil.successResponse(users);
        } catch (Exception e) {
            log.error("UserController getAllUser Error: ", e);
            return ResponseUtil.failedResponse("查询所有用户失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Object addUser(UserModel userModel) {
        try {
            userService.addUser(userModel);
            return ResponseUtil.successResponse(userModel);
        } catch (Exception e) {
            log.error("UserController addUser Error: ", e);
            return ResponseUtil.failedResponse("新增用户失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Object updateUser(UserModel userModel) {
        try {
            userService.updateUser(userModel);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("UserController updateUser Error: ", e);
            return ResponseUtil.failedResponse("修改用户失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/batchDeleteUser", method = RequestMethod.POST)
    public Object batchDeleteUser(String ids) {
        try {
            List<String> idList = Arrays.asList(ids.split(","));
            userService.batchDeleteUser(idList);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("UserController deleteUser Error: ", e);
            return ResponseUtil.failedResponse("删除用户失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public Object getUserInfo(String userName) {
        try {
            UserModel user = userService.getUserByUserName(userName);
            return ResponseUtil.successResponse(user);
        } catch (Exception e) {
            log.error("UserController getUserInfo Error: ", e);
            return ResponseUtil.failedResponse("查询用户失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/downloadUsers", method = RequestMethod.GET)
    public Object downloadUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            userService.downloadUsers(request, response);
        } catch (Exception e) {
            log.error("OperatorLogController exportOperateLog Error: ", e);
            return ResponseUtil.failedResponse("导出操作日志失败！", e.getMessage());
        }
        return null;
    }
}
