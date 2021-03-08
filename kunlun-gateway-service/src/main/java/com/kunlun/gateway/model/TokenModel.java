package com.kunlun.gateway.model;

public class TokenModel {
    private String token;

    private UserModel userInfo;

    public TokenModel() {}

    public TokenModel(String token, UserModel userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    public UserModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserModel userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
