package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.dao.IOnlineDao;
import com.kunlun.basedata.dao.IUserDao;
import com.kunlun.basedata.model.HomeCountModel;
import com.kunlun.basedata.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class HomeService implements IHomeService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IOnlineDao onlineDao;

    @Override
    public HomeCountModel getUserCount() throws Exception {
        // 注册用户数
        int userCount = userDao.getUsersCount(new HashMap<>());

        // 在线用户数
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("online", "true");
        int onlineCount = onlineDao.getOnlinesCount(queryMap);

        // 用户数统计返回
        HomeCountModel countModel = new HomeCountModel();
        countModel.setUserCount(userCount);
        countModel.setOnlineCount(onlineCount);
        return countModel;
    }
}
