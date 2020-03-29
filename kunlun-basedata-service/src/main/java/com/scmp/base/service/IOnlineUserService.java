package com.scmp.base.service;

import com.scmp.base.model.OnlineUserModel;
import com.scmp.common.model.Page;

import javax.servlet.http.HttpServletRequest;

public interface IOnlineUserService {
    public Page getAllOnlineUser(OnlineUserModel onlineUserModel, int currentPage, int pageSize) throws Exception;

    public void addOnlineUser(HttpServletRequest request, String userName) throws Exception;

    public void updateOnlineUser(String userName) throws Exception;
}
