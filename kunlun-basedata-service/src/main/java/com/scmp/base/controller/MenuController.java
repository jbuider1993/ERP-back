package com.scmp.base.controller;

import com.scmp.base.model.MenuModel;
import com.scmp.base.service.IMenuService;
import com.scmp.common.model.Page;
import com.scmp.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/getAllMenu", method = RequestMethod.GET)
    public Object getAllMenu(MenuModel menuModel, int currentPage, int pageSize) {
        try {
            Page menus = menuService.getAllMenu(menuModel, currentPage, pageSize);
            return ResponseUtil.successResponse(menus);
        } catch (Exception e) {
            log.error("MenuController getAllMenu Error: ", e);
            return ResponseUtil.failedResponse("获取系统菜单失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public Object addMenu(MenuModel menuModel) {
        try {
            menuService.addMenu(menuModel);
            return ResponseUtil.successResponse(menuModel);
        } catch (Exception e) {
            log.error("MenuController addMenu Error: ", e);
            return ResponseUtil.failedResponse("新增系统菜单失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/editMenu", method = RequestMethod.POST)
    public Object editMenu(MenuModel menuModel) {
        try {
            menuService.editMenu(menuModel);
            return ResponseUtil.successResponse(menuModel);
        } catch (Exception e) {
            log.error("MenuController editMenu Error: ", e);
            return ResponseUtil.failedResponse("编辑系统菜单失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    public Object deleteMenu(String id) {
        try {
            String[] ids = id.split(",");
            menuService.deleteMenu(ids);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("MenuController deleteMenu Error: ", e);
            return ResponseUtil.failedResponse("删除系统菜单失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/getAppMenu", method = RequestMethod.GET)
    public Object getAppMenu() {
        try {
            Map<String, Object> menus = menuService.getAppMenu();
            return ResponseUtil.successResponse(menus);
        } catch (Exception e) {
            log.error("MenuController getAllMenu Error: ", e);
            return ResponseUtil.failedResponse("获取系统平台菜单！", e.getMessage());
        }
    }
}
