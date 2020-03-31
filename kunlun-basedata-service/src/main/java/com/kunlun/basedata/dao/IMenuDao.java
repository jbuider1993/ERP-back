package com.kunlun.basedata.dao;

import com.kunlun.basedata.model.MenuModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IMenuDao {
    public List<MenuModel> getAllMenu(Map<String, Object> queryMap) throws Exception;

    public int getMenusCount(Map<String, Object> queryMap) throws Exception;

    public void addMenu(MenuModel menuModel) throws Exception;

    public void editMenu(MenuModel menuModel) throws Exception;

    public void deleteMenu(String[] ids) throws Exception;
}
