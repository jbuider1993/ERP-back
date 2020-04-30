package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.service.IMenuService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.basedata.dao.IMenuDao;
import com.kunlun.basedata.model.MenuModel;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.JsonUtil;
import com.kunlun.common.utils.ListPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService implements IMenuService {

    @Autowired
    private IMenuDao menuDao;

    @Override
    public Page getAllMenu(MenuModel menuModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(menuModel, startIndex, pageSize);

        List<MenuModel> list = menuDao.getAllMenu(queryMap);
        List<MenuModel> roots = list.parallelStream().filter(obj -> StringUtils.isEmpty(obj.getParentId())).collect(Collectors.toList());
        Map<String, String> map = list.stream().collect(Collectors.toMap(MenuModel::getId, MenuModel::getName));
        constructTreeMenu(roots, list, map);

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(menuDao.getMenusCount(queryMap));
        page.setRecords(roots);
        return ListPageUtil.limitPages(roots, startIndex, pageSize);
    }

    private void constructTreeMenu(List<MenuModel> roots, List<MenuModel> list, Map<String, String> map) {
        for (MenuModel model : roots) {
            List<MenuModel> childrens = list.stream().filter(obj -> model.getId().equals(obj.getParentId())).collect(Collectors.toList());
            model.setParent(map.get(model.getParentId()));

            if (null != childrens && childrens.size() > 0) {
                model.setChildren(childrens);
                constructTreeMenu(childrens, list, map);
            }
        }
    }

    @Override
    public void addMenu(MenuModel menuModel) throws Exception {
        String id = CommonUtil.generateUUID();
        String longCode = StringUtils.isEmpty(menuModel.getLongCode()) ? id : menuModel.getLongCode() + "_" + id;
        menuModel.setId(id);
        menuModel.setLongCode(longCode);
        menuModel.setCreateTime(new Date());
        menuModel.setModifiedTime(new Date());
        menuDao.addMenu(menuModel);
    }

    @Override
    public void editMenu(MenuModel menuModel) throws Exception {
        menuModel.setModifiedTime(new Date());
//        menuDao.editMenu(menuModel);
    }

    @Override
    public void deleteMenu(String[] ids) throws Exception {
        menuDao.deleteMenu(ids);
    }

    @Override
    public Map<String, Object> getAppMenu() throws Exception {
        // 设置菜单
        List<MenuModel> list = menuDao.getAllMenu(new HashMap<>());
        List<MenuModel> rootMenus = list.stream().filter(obj -> StringUtils.isEmpty(obj.getParentId())).collect(Collectors.toList());
        List<MenuModel> mainMenu = JsonUtil.copyArray(rootMenus, MenuModel.class);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("main", mainMenu);
        List<MenuModel> menuList = JsonUtil.copyArray(list, MenuModel.class);
        resultMap.put("list", menuList);

        // 组装成Tree，并设置目录
        packageTreeMenu(list, rootMenus);
        Map<String, List<MenuModel>> siderMenu = new HashMap<>();
        for (MenuModel model : rootMenus) {
            if (!ObjectUtils.isEmpty(model.getChildren()) && model.getChildren().size() > 0) {
                siderMenu.put(model.getKey(), model.getChildren());
            }
        }
        resultMap.put("sider", siderMenu);
        return resultMap;
    }

    private void packageTreeMenu(List<MenuModel> list, List<MenuModel> rootMenus) {
        for (MenuModel model : rootMenus) {
           List<MenuModel> childrens = list.stream().filter(obj -> !ObjectUtils.isEmpty(obj.getParentId()) && obj.getParentId().equals(model.getId())).collect(Collectors.toList());
           boolean isChild = !ObjectUtils.isEmpty(childrens) && childrens.size() > 0;
           model.setChildren(isChild ? childrens : null);

           if (isChild) {
               packageTreeMenu(list, childrens);
           }
        }
    }
}
