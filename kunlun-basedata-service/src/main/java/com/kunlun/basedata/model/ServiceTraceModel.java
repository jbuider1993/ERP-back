package com.kunlun.basedata.model;

import com.kunlun.common.model.BaseModel;

public class ServiceTraceModel extends BaseModel {

    private String traceId;

    private String name;

    private String kind;

    private String duration;

    private long timestamp;

    private String localServceName;

    private String remoteServceName;

    private String ipv4;

    private String port;

    private String path;

    private String clz;

    private String method;

    private String type;

    private String statusCode;

    private String error;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocalServceName() {
        return localServceName;
    }

    public void setLocalServceName(String localServceName) {
        this.localServceName = localServceName;
    }

    public String getRemoteServceName() {
        return remoteServceName;
    }

    public void setRemoteServceName(String remoteServceName) {
        this.remoteServceName = remoteServceName;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
