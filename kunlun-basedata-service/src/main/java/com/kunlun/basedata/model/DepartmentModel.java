package com.kunlun.basedata.model;

import com.kunlun.common.model.TreeModel;

/**
 * 系统部门模型
 */
public class DepartmentModel extends TreeModel<DepartmentModel> {

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门编号
     */
    private String departmentCode;

    /**
     * 部门类型(1——集团或公司；2-子公司；3-部门)
     */
    private int type;

    /**
     * 状态(0——正常；1-停用)
     */
    private int status;

    /**
     * 职责描述
     */
    private String dutyDesc;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
