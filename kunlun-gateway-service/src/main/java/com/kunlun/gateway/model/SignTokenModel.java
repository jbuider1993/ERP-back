package com.kunlun.gateway.model;

public class SignTokenModel {

    private String id;

    private String userName;

    private String password;

    private String loginTime;

    private String secret;

    private Integer expireTime;

    public SignTokenModel() {}

    public SignTokenModel(String id, String userName, String password, String loginTime, String secret, Integer expireTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.loginTime = loginTime;
        this.secret = secret;
        this.expireTime = expireTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }
}
