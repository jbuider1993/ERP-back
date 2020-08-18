package com.kunlun.basedata.task;

import com.kunlun.basedata.model.OnlineUserModel;
import com.kunlun.basedata.service.IOnlineUserService;
import com.kunlun.basedata.service.IRedisService;
import com.kunlun.basedata.utils.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 删除过期的token
 */
public class ScheduleCacheTask implements Runnable {

    private static Logger log = LogManager.getLogger();

    private static final String KEY_PATTERN = "admin_*";

    private static final String TOKEN_KEY_TIME = "loginTime";

    private static final String TIME_PATTERN = "yyyyMMddHHmmss";

    private static final long HALF_HOUR = 30 * 60 * 1000;

    private IRedisService redisService;

    private IOnlineUserService onlineUserService;

    public ScheduleCacheTask(IRedisService redisService, IOnlineUserService onlineUserService) {
        this.redisService = redisService;
        this.onlineUserService = onlineUserService;
    }

    @Override
    public void run() {
        try {
            // 删除Redis上过期的token
            clearCachedLoginStatus();

            // 清理DB中在线用户
            clearDBLoginStatus();
        } catch (Exception e) {
            log.error("ScheduleCacheTask Error", e);
        }
    }

    public void clearCachedLoginStatus() throws Exception {
        Set<String> keys = (Set<String>) redisService.keys(KEY_PATTERN, 1);
        for (String key : keys) {
            String value = (String) redisService.get(key, 1);
            String date = JwtTokenUtil.getTokenInfo(value, TOKEN_KEY_TIME);

            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
            Date loginTime = dateFormat.parse(date);
            Date delay = new Date(loginTime.getTime() - HALF_HOUR);
            long minus = new Date().getTime() - delay.getTime();
            if (minus > HALF_HOUR) {
                redisService.del(key, 1);
                log.info("deleting timeout token, key: " + key + ", token: " + value);
            }
        }
    }

    public void clearDBLoginStatus() throws Exception {
        List<OnlineUserModel> onlineUserModels = onlineUserService.queryAllOnlineUser();
        List<String> onlineUserIds = new ArrayList<>();
        for (OnlineUserModel onlineUser : onlineUserModels) {
            Date delay = new Date(onlineUser.getLastTime().getTime() - HALF_HOUR);
            long minus = new Date().getTime() - delay.getTime();
            if (minus > HALF_HOUR) {
                onlineUserIds.add(onlineUser.getId());
            }
        }

        if (!ObjectUtils.isEmpty(onlineUserIds)) {
            onlineUserService.updateOnlineStatus(onlineUserIds);
        }
    }
}
