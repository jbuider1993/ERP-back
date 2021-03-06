package com.kunlun.basedata.service;

import com.kunlun.basedata.model.OnlineUserModel;
import com.kunlun.basedata.model.vo.StatisticUserVo;
import com.kunlun.common.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IOnlineUserService {
    public Page getAllOnlineUser(OnlineUserModel onlineUserModel, int currentPage, int pageSize) throws Exception;

    public void addOnlineUser(HttpServletRequest request, String id, String userName, Date loginDate) throws Exception;

    public void updateOnlineUser(String id) throws Exception;

    public List<OnlineUserModel> queryAllOnlineUser() throws Exception;

    public void updateOnlineStatus(List<String> onlineUserIds) throws Exception;

    public List<StatisticUserVo> statisticOnlineByYear(String year) throws Exception;

    public void downloadOnlineUsers(HttpServletResponse response, HttpServletRequest request, OnlineUserModel onlineUserModel) throws Exception;

    public void forceExit(String onlineUsers) throws Exception;
}
