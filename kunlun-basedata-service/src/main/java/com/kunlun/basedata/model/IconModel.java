package com.kunlun.basedata.model;

import com.kunlun.common.model.BaseModel;

/**
 * 系统平台图标模型
 */
public class IconModel extends BaseModel {

    /**
     * 图标名称
     */
    private String name;

    /**
     * 图标key
     */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
