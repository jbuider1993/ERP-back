package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.UserModel;
import com.kunlun.basedata.service.IUserService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.basedata.dao.IUserDao;
import com.kunlun.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
}
