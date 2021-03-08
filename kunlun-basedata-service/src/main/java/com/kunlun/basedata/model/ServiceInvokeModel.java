package com.kunlun.basedata.model;

/**
 * 服务调用情况统计模型
 */
public class ServiceInvokeModel {

    private String id;

    private String serviceName;

    private String requestType;

    private long count;

    private long successAccess;

    private long duration;

    private String available;

    private String ipv4;

    private String port;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public long getSuccessAccess() {
        return successAccess;
    }

    public void setSuccessAccess(long successAccess) {
        this.successAccess = successAccess;
    }
}
