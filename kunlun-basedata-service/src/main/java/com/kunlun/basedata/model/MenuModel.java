package com.kunlun.basedata.model;

import com.kunlun.common.model.TreeModel;

/**
 * 系统平台菜单模型
 */
public class MenuModel extends TreeModel<MenuModel> {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单key
     */
    private String key;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否显示
     */
    private boolean show;

    /**
     * 是否携带Token
     */
    private boolean carryToken;

    /**
     * 父节点名称
     */
    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public boolean getShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean getCarryToken() {
        return carryToken;
    }

    public void setCarryToken(boolean carryToken) {
        this.carryToken = carryToken;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
