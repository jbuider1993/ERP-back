package com.kunlun.basedata.model;

import com.kunlun.common.model.BaseModel;

/**
 *
 */
public class RoleModel extends BaseModel {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色字符
     */
    private String roleWord;

    /**
     * 状态
     */
    private boolean status;

    /**
     * 菜单权限
     */
    private String menuIds;

    /**
     * 用户权限
     */
    private String userIds;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleWord() {
        return roleWord;
    }

    public void setRoleWord(String roleWord) {
        this.roleWord = roleWord;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
