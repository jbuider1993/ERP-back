package com.kunlun.system.model;

import java.util.Date;

/**
 * 模型model
 */
public class ModelModel {
    private String id;

    private String modelName;

    private String modelKey;

    private String version;

    private String category;

    private String metaInfo;

    private String deploymentId;

    private boolean deployStatus;

    private Date createTime;

    private Date modifiedTime;

    private Date deployTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public boolean isDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(boolean deployStatus) {
        this.deployStatus = deployStatus;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }
}
