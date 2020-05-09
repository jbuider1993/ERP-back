package com.kunlun.basedata.controller;

import com.kunlun.basedata.model.RoleModel;
import com.kunlun.basedata.service.IRoleService;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/getAllRole", method = RequestMethod.GET)
    public Object getAllRole(RoleModel roleModel, int currentPage, int pageSize) {
        try {
            Page roles = roleService.getAllRole(roleModel, currentPage, pageSize);
            return ResponseUtil.successResponse(roles);
        } catch (Exception e) {
            log.error("RoleController getAllRole Error: ", e);
            return ResponseUtil.failedResponse("查询所有角色失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public Object addRole(RoleModel roleModel) {
        try {
            roleService.addRole(roleModel);
            return ResponseUtil.successResponse(roleModel);
        } catch (Exception e) {
            log.error("RoleController addRole Error: ", e);
            return ResponseUtil.failedResponse("新增角色失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public Object updateRole(RoleModel roleModel) {
        try {
            roleService.updateRole(roleModel);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("RoleController updateRole Error: ", e);
            return ResponseUtil.failedResponse("修改角色失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateMenuLimit", method = RequestMethod.POST)
    public Object updateMenuLimit(RoleModel roleModel) {
        try {
            roleService.updateMenuLimit(roleModel);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("RoleController updateRole Error: ", e);
            return ResponseUtil.failedResponse("修改角色失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateAllotUser", method = RequestMethod.POST)
    public Object updateAllotUser(RoleModel roleModel) {
        try {
            RoleModel result = roleService.updateAllotUser(roleModel);
            return ResponseUtil.successResponse(result);
        } catch (Exception e) {
            log.error("RoleController updateRole Error: ", e);
            return ResponseUtil.failedResponse("修改角色失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/batchDeleteRole", method = RequestMethod.POST)
    public Object batchDeleteRole(String ids) {
        try {
            List<String> idList = Arrays.asList(ids.split(","));
            roleService.deleteRole(idList);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("RoleController batchDeleteRole Error: ", e);
            return ResponseUtil.failedResponse("删除角色失败！", e.getMessage());
        }
    }
}
