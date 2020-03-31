package com.kunlun.basedata.service;

import com.kunlun.basedata.model.MenuModel;
import com.scmp.common.model.Page;

import java.util.Map;

public interface IMenuService {
    public Page getAllMenu(MenuModel menuModel, int currentPage, int pageSize) throws Exception;

    public void addMenu(MenuModel menuModel) throws Exception;

    public void editMenu(MenuModel menuModel) throws Exception;

    public void deleteMenu(String[] ids) throws Exception;

    public Map<String, Object> getAppMenu() throws Exception;
}
