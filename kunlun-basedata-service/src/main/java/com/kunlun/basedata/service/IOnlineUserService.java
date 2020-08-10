package com.kunlun.basedata.service;

import com.kunlun.basedata.model.OnlineUserModel;
import com.kunlun.common.model.Page;

import javax.servlet.http.HttpServletRequest;

public interface IOnlineUserService {
    public Page getAllOnlineUser(OnlineUserModel onlineUserModel, int currentPage, int pageSize) throws Exception;

    public OnlineUserModel addOnlineUser(HttpServletRequest request, String userName) throws Exception;

    public void updateOnlineUser(String userName, String loginTime) throws Exception;
}