package com.kunlun.common.model;

import java.util.List;

/**
 * 树形数据模型
 *
 * @param <T>   泛型类型
 */
public class TreeModel<T> extends BaseModel {

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 长ID
     */
    private String longCode;

    /**
     * 是否是叶子节点
     */
    private boolean isLeaf;

    /**
     * 叶子节点集合
     */
    private List<T> children;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLongCode() {
        return longCode;
    }

    public void setLongCode(String longCode) {
        this.longCode = longCode;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
