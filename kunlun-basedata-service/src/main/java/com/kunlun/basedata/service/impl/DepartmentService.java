package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.dao.IDepartmentDao;
import com.kunlun.basedata.model.DepartmentModel;
import com.kunlun.basedata.service.IDepartmentService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.TreeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门业务Service类
 */
@Service
@Transactional
public class DepartmentService implements IDepartmentService {

    private final Logger logger = LogManager.getLogger();

    @Autowired
    private IDepartmentDao departmentDao;

    @Override
    public Page<DepartmentModel> getAllDepartment(DepartmentModel departmentModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(departmentModel, startIndex, pageSize);
        int count = departmentDao.getDepartmentCount(queryMap);
        List<DepartmentModel> allDepartment = departmentDao.getAllDepartment(queryMap);

        List<DepartmentModel> rootNodes = allDepartment.stream().filter(obj -> ObjectUtils.isEmpty(obj.getParentId())).collect(Collectors.toList());
        TreeUtil.packageListToTree(rootNodes, allDepartment);

        Page<DepartmentModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(rootNodes);
        return page;
    }

    @Override
    public void addDepartment(DepartmentModel departmentModel) throws Exception {
        departmentModel.setId(CommonUtil.generateUUID());
        String longCode = ObjectUtils.isEmpty(departmentModel.getLongCode()) ? "" : (departmentModel.getLongCode() + "_");
        departmentModel.setLongCode(longCode + departmentModel.getId());
        departmentDao.addDepartment(departmentModel);
    }

    @Override
    public void updateDepartment(DepartmentModel departmentModel) throws Exception {
        departmentDao.updateDepartment(departmentModel);
    }

    @Override
    public void deleteDepartment(List<String> list) throws Exception {
        departmentDao.deleteDepartment(list);
    }
}
