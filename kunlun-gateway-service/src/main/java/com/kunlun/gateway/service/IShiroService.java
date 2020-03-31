package com.kunlun.gateway.service;

import com.kunlun.gateway.model.TokenModel;

import javax.servlet.http.HttpServletRequest;

public interface IShiroService {
    public TokenModel handleLogin(String userName, String password) throws Exception;

    public void handleLogout(HttpServletRequest request, String userName) throws Exception;
}
