package com.kunlun.common.model;

/**
 * 当前登录账号模型
 */
public class CurrentAccount {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录时间
     */
    private String loginTime;

    /**
     * Token
     */
    private String clientToken;

    public CurrentAccount() {
    }

    public CurrentAccount(ClientToken clientToken) {
        this.userId = clientToken.getUserId();
        this.userName = clientToken.getUserName();
        this.loginTime = clientToken.getLoginTime();
        this.clientToken = clientToken.getClientToken();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
