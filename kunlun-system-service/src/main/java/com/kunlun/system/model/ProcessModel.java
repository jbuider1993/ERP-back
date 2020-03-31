package com.kunlun.system.model;

import java.util.Date;

/**
 * 流程model
 */
public class ProcessModel {
    private String id;

    private String processName;

    private String key;

    private String modelId;

    private String modelName;

    private String deploymentId;

    private String processStatus;

    private String processInstanceId;

    private String currentExecuteKey;

    private String currentExecuteName;

    private String nextExecuteKey;

    private String nextExecuteName;

    private Date startTime;

    private Date endTime;

    private String dataType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getCurrentExecuteKey() {
        return currentExecuteKey;
    }

    public void setCurrentExecuteKey(String currentExecuteKey) {
        this.currentExecuteKey = currentExecuteKey;
    }

    public String getCurrentExecuteName() {
        return currentExecuteName;
    }

    public void setCurrentExecuteName(String currentExecuteName) {
        this.currentExecuteName = currentExecuteName;
    }

    public String getNextExecuteKey() {
        return nextExecuteKey;
    }

    public void setNextExecuteKey(String nextExecuteKey) {
        this.nextExecuteKey = nextExecuteKey;
    }

    public String getNextExecuteName() {
        return nextExecuteName;
    }

    public void setNextExecuteName(String nextExecuteName) {
        this.nextExecuteName = nextExecuteName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
