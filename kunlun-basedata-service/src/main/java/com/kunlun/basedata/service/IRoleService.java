package com.kunlun.basedata.service;

import com.kunlun.basedata.model.RoleModel;
import com.kunlun.common.model.Page;

import java.util.List;

public interface IRoleService {
    public Page getAllRole(RoleModel roleMode, int currentPage, int pageSize) throws Exception;

    public void addRole(RoleModel roleMode) throws Exception;

    public void updateRole(RoleModel roleMode) throws Exception;

    public RoleModel updateMenuLimit(RoleModel roleModel) throws Exception;

    public RoleModel updateAllotUser(RoleModel roleModel) throws Exception;

    public void deleteRole(List<String> ids) throws Exception;

    public RoleModel getRoleById(String id) throws Exception;

    public RoleModel getRoleByUserId(String userId) throws Exception;
}
