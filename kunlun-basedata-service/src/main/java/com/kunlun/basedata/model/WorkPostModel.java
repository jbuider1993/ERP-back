package com.kunlun.basedata.model;

import com.kunlun.common.model.BaseModel;

/**
 * 系统岗位模型
 */
public class WorkPostModel extends BaseModel {

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位编号
     */
    private String postCode;

    /**
     * 职责描述
     */
    private String dutyDesc;

    /**
     * 状态(0——正常；1-停用)
     */
    private int status;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDutyDesc() {
        return dutyDesc;
    }

    public void setDutyDesc(String dutyDesc) {
        this.dutyDesc = dutyDesc;
    }
}
