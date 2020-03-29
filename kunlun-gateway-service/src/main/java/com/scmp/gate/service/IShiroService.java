package com.scmp.gate.service;

import com.scmp.gate.model.TokenModel;

import javax.servlet.http.HttpServletRequest;

public interface IShiroService {
    public TokenModel handleLogin(String userName, String password) throws Exception;

    public void handleLogout(HttpServletRequest request, String userName) throws Exception;
}
