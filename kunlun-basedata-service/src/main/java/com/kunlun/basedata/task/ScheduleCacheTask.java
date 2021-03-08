package com.kunlun.basedata.task;

import com.kunlun.basedata.service.IOnlineUserService;
import com.kunlun.basedata.service.IRedisService;
import com.kunlun.basedata.utils.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 删除过期的token
 */
public class ScheduleCacheTask implements Runnable {

    private static Logger log = LogManager.getLogger();

    private static final String KEY_PATTERN = "admin_*";

    private static final String TOKEN_KEY_TIME = "loginTime";

    private static final String TOKEN_ONLINE_USER_ID = "onlineUserId";

    private static final String TIME_PATTERN = "yyyyMMddHHmmss";

    private static final long HALF_HOUR = 30 * 60 * 1000;

    private ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    private IRedisService redisService;

    private IOnlineUserService onlineUserService;

    public ScheduleCacheTask(IRedisService redisService, IOnlineUserService onlineUserService) {
        this.redisService = redisService;
        this.onlineUserService = onlineUserService;
    }

    @Override
    public void run() {
        try {
            threadLocal.set(new ArrayList<>());

            // 删除Redis上过期的token
            clearCachedLoginStatus();

            // 清理DB中在线用户
            clearDBLoginStatus();

            threadLocal.remove();
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
                String onlineUserId = JwtTokenUtil.getTokenInfo(value, TOKEN_ONLINE_USER_ID);
                List<String> onlineUsers = threadLocal.get();
                onlineUsers.add(onlineUserId);
                redisService.del(key, 1);
                log.info("deleting timeout token, key: " + key + ", token: " + value);
            }
        }
    }

    public void clearDBLoginStatus() throws Exception {
        List<String> onlineUserIds = threadLocal.get();
        if (!ObjectUtils.isEmpty(onlineUserIds)) {
            onlineUserService.updateOnlineStatus(onlineUserIds);
        }
    }
}
