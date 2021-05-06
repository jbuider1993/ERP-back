package com.kunlun.basedata.controller;

import com.kunlun.basedata.model.DepartmentModel;
import com.kunlun.basedata.service.IDepartmentService;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 部门Controller类
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping(value = "/getAllDepartment", method = RequestMethod.GET)
    public Object getAllDepartment(DepartmentModel departmentModel, int currentPage, int pageSize) {
        try {
            Page<DepartmentModel> departments = departmentService.getAllDepartment(departmentModel, currentPage, pageSize);
            return ResponseUtil.successResponse(departments);
        } catch (Exception e) {
            logger.error("DepartmentController getAllDepartment Error: ", e);
            return ResponseUtil.failedResponse("获取部门数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public Object addDepartment(DepartmentModel departmentModel) {
        try {
            departmentService.addDepartment(departmentModel);
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            logger.error("DepartmentController addDepartment Error: ", e);
            return ResponseUtil.failedResponse("添加部门失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
    public Object updateDepartment(DepartmentModel departmentModel) {
        try {
            departmentService.updateDepartment(departmentModel);
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            logger.error("DepartmentController updateDepartment Error: ", e);
            return ResponseUtil.failedResponse("更新部门失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
    public Object deleteDepartment(String ids) {
        try {
            List<String> list = Arrays.asList(ids.split(","));
            departmentService.deleteDepartment(list);
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            logger.error("DepartmentController deleteDepartment Error: ", e);
            return ResponseUtil.failedResponse("删除部门失败！", e.getMessage());
        }
    }
}
