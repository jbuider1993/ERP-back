package com.scmp.base.service;

import com.scmp.base.model.RoleModel;
import com.scmp.common.model.Page;

import java.util.List;

public interface IRoleService {
    public Page getAllRole(RoleModel roleMode, int currentPage, int pageSize) throws Exception;

    public void addRole(RoleModel roleMode) throws Exception;

    public void updateRole(RoleModel roleMode) throws Exception;

    public void batchDeleteRole(List<String> ids) throws Exception;
}
