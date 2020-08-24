package com.kunlun.basedata.service;

import com.kunlun.basedata.model.OnlineUserModel;
import com.kunlun.basedata.model.vo.StatisticUserVo;
import com.kunlun.common.model.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IOnlineUserService {
    public Page getAllOnlineUser(OnlineUserModel onlineUserModel, int currentPage, int pageSize) throws Exception;

    public OnlineUserModel addOnlineUser(HttpServletRequest request, String userName) throws Exception;

    public void updateOnlineUser(String userName, String loginTime) throws Exception;

    public List<OnlineUserModel> queryAllOnlineUser() throws Exception;

    public void updateOnlineStatus(List<String> onlineUserIds) throws Exception;

    public List<StatisticUserVo> statisticOnlineByYear(String year) throws Exception;
}
