package com.scmp.base.dao;

import com.scmp.base.model.RoleModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IRoleDao {
    public List<RoleModel> getAllRole(Map<String, Object> queryMap) throws Exception;

    public int getRolesCount(Map<String, Object> queryMap) throws Exception;

    public void addRole(RoleModel roleModel) throws Exception;

    public void updateRole(RoleModel roleModel) throws Exception;

    public void batchDeleteRole(List<String> ids) throws Exception;
}
