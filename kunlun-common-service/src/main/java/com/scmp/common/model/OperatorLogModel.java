package com.scmp.common.model;

import java.util.Date;

/**
 * 操作日志模型
 */
public class OperatorLogModel {
    // 日志业务Id
    private String id;

    // 登录IP地址
    private String ip;

    // 登录用户名
    private String userName;

    // 操作类型
    private int operatorType;

    // 操作描述
    private String operateDescription;

    // 请求URL
    private String requestUrl;

    // 请求协议
    private String protocal;

    // 请求参数
    private String params;

    // 请求方式
    private String style;

    // 访问服务名
    private String serviceName;

    // 访问服务端口
    private int port;

    // 运行线程名
    private String threadName;

    // 访问类名
    private String clzName;

    // 调用方法
    private String methodName;

    // 异常信息
    private String exceptionInfo;

    // 访问状态
    private String status;

    // 访问时间
    private Date operateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperateDescription() {
        return operateDescription;
    }

    public void setOperateDescription(String operateDescription) {
        this.operateDescription = operateDescription;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}