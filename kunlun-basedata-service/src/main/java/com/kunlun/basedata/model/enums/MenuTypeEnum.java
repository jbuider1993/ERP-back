package com.kunlun.basedata.model.enums;

/**
 * 菜单类型枚举
 */
public enum MenuTypeEnum {
    MENU("1", "菜单"),
    CATALOGUE("2", "目录");

    private String key;

    private String desc;

    MenuTypeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
