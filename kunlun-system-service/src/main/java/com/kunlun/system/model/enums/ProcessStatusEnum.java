package com.kunlun.system.model.enums;

public enum ProcessStatusEnum {

    UNSUBMIT("0", "未提交"),

    AUDITING("1", "审核中"),

    ABORT("2", "驳回"),

    ABOLISH("3", "废止"),

    FINISH("4", "已完成");

    ProcessStatusEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    private String key;

    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
