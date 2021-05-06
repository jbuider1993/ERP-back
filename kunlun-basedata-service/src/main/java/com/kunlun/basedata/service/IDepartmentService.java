package com.kunlun.basedata.service;

import com.kunlun.basedata.model.DepartmentModel;
import com.kunlun.basedata.model.IconModel;
import com.kunlun.common.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 部门业务接口
 */
public interface IDepartmentService {

    public Page<DepartmentModel> getAllDepartment(DepartmentModel departmentModel, int currentPage, int pageSize) throws Exception;

    public void addDepartment(DepartmentModel departmentModel) throws Exception;

    public void updateDepartment(DepartmentModel departmentModel) throws Exception;

    public void deleteDepartment(List<String> list) throws Exception;
}
