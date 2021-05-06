package com.kunlun.basedata.model;

import com.kunlun.common.model.BaseModel;

/**
 * 验证码模型类
 */
public class CodeModel extends BaseModel {

    private String code;

    private String binary;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }
}
