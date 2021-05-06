package com.kunlun.basedata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kunlun.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 系统在线用户模型类
 */
public class OnlineUserModel extends BaseModel {

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录主机
     */
    private String loginIp;

    /**
     * 登录地点
     */
    private String loginAddress;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 浏览器
     */
    private String usedBrowser;

    /**
     * 操作系统
     */
    private String usedWindow;

    /**
     * 登录状态
     */
    private boolean online;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date loginTime;

    /**
     * 最后访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date lastTime;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public boolean getOnline() {
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
