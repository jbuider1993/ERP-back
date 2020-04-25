package com.kunlun.basedata.service;

import com.kunlun.basedata.model.UserModel;
import com.kunlun.common.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserService {
    public Page getAllUser(UserModel userMode, int currentPage, int pageSize) throws Exception;

    public void addUser(UserModel user) throws Exception;

    public void updateUser(UserModel user) throws Exception;

    public void batchDeleteUser(List<String> ids) throws Exception;

    public UserModel getUserByUserName(String userName) throws Exception;

    public void downloadUsers(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
