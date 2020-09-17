package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.UserModel;
import com.kunlun.basedata.service.IUserService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.basedata.dao.IUserDao;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public Page getAllUser(UserModel userMode, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(userMode, startIndex, pageSize);

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(userDao.getUsersCount(queryMap));
        page.setRecords(userDao.getAllUser(queryMap));
        return page;
    }

    @Override
    public void addUser(UserModel user) throws Exception {
        String userId = UUID.randomUUID().toString().replace("-", "");
        user.setId(userId);
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        userDao.addUser(user);
    }

    @Override
    public void updateUser(UserModel user) throws Exception {
        userDao.updateUser(user);
    }

    @Override
    public void batchDeleteUser(List<String> ids) throws Exception {
        userDao.batchDeleteUser(ids);
    }

    @Override
    public UserModel getUserByUserName(String userName) throws Exception {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public void downloadUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(new UserModel(), 0, 99999);
        List<UserModel> userlogList = userDao.getAllUser(queryMap);
        String[] headerNames = new String[]{"用户名", "密码", "电话号码", "邮箱", "创建时间", "更新时间"};
        String[] fieldNames = new String[]{"userName", "password", "phoneNumber", "email", "createTime", "modifiedTime"};
        int[] lineWidths = new int[]{40, 40, 50, 50, 80, 80};

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dataSource", userlogList);
        paramMap.put("sheetName", "人员用户");
        paramMap.put("headerNames", headerNames);
        paramMap.put("fieldNames", fieldNames);
        paramMap.put("lineWidths", lineWidths);
        ExcelUtil.exportExcel(request, response, UserModel.class, paramMap);
    }
}
