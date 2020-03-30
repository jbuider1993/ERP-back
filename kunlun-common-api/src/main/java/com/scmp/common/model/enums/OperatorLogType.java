package com.scmp.common.model.enums;

/**
 * 操作日志类型枚举
 */
public enum OperatorLogType {
    LOGIN(1, "登录与注销"),

    SYSTEM(2, "系统操作"),

    DATA(3, "数据操作");

    private int key;

    private String name;

    OperatorLogType(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}