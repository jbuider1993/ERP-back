package com.kunlun.system.config.dataSource;

public enum DataSourceType {
    MASTER("master", "主数据源"),
    SLAVE("slave", "备数据源"),
    ACTIVITI("activiti", "Activiti数据源");

    private String key;

    private String desc;

    DataSourceType(String key, String desc) {
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
