package com.kunlun.gateway.model;

import java.util.Date;

public class OnlineUserModel {
    private String id;
    private String loginName;
    private String loginIp;
    private String loginAddress;
    private String usedBrowser;
    private String usedWindow;
    private boolean online;
    private Date loginTime;
    private Date lastTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getUsedBrowser() {
        return usedBrowser;
    }

    public void setUsedBrowser(String usedBrowser) {
        this.usedBrowser = usedBrowser;
    }

    public String getUsedWindow() {
        return usedWindow;
    }

    public void setUsedWindow(String usedWindow) {
        this.usedWindow = usedWindow;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
