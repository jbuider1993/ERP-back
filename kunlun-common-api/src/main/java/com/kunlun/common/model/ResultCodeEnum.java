package com.kunlun.common.model;

/**
 * 响应码枚举类
 */
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未认证（签名错误）"),
    NOTFOUNDRESOURCE(404, "接口不存在"),
    SERVERERROR(500, "服务器内部错误");

    public int code;
    public String describe;

    ResultCodeEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
