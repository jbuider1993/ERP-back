package com.kunlun.basedata.dao;

import com.kunlun.basedata.model.DepartmentModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门数据库Dao
 */
@Repository
public interface IDepartmentDao {

    public List<DepartmentModel> getAllDepartment(Map<String, Object> queryMap) throws Exception;

    public int getDepartmentCount(Map<String, Object> queryMap) throws Exception;

    public void addDepartment(DepartmentModel departmentModel) throws Exception;

    public void updateDepartment(DepartmentModel departmentModel) throws Exception;

    public void deleteDepartment(List<String> list) throws Exception;
}
