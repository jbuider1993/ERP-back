package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.RoleModel;
import com.kunlun.basedata.service.IRoleService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.basedata.dao.IRoleDao;
import com.kunlun.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public Page getAllRole(RoleModel roleMode, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(roleMode, startIndex, pageSize);

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(roleDao.getRolesCount(queryMap));
        page.setRecords(roleDao.getAllRole(queryMap));
        return page;
    }

    @Override
    public void addRole(RoleModel roleMode) throws Exception {
        roleDao.addRole(roleMode);
    }

    @Override
    public void updateRole(RoleModel roleMode) throws Exception {
        roleDao.updateRole(roleMode);
    }

    @Override
    public void batchDeleteRole(List<String> ids) throws Exception {
        roleDao.batchDeleteRole(ids);
    }
}
