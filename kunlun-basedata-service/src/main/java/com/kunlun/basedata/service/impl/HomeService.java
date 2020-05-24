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
        // 总访问量
        HashMap<String, Object> newMap = new HashMap<>();
        int userCount = onlineDao.getOnlinesCount(newMap);

        // 在线用户数
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("online", "true");
        int onlineCount = onlineDao.getOnlinesCount(queryMap);

        // 最近一个月内访问量
        int leastMonthCount = onlineDao.getCountLeastMonth(newMap);
        HomeCountModel countModel = new HomeCountModel();
        countModel.setUserCount(userCount);
        countModel.setOnlineCount(onlineCount);
        countModel.setLeastCount(leastMonthCount);
        return countModel;
    }
}
