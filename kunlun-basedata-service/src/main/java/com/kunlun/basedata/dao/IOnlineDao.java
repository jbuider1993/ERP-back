package com.kunlun.basedata.dao;

import com.kunlun.basedata.model.OnlineUserModel;
import com.kunlun.basedata.model.vo.StatisticUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface IOnlineDao {
    public List<OnlineUserModel> getAllOnlineUser(Map<String, Object> queryMap) throws Exception;

    public int getOnlinesCount(Map<String, Object> queryMap) throws Exception;

    public int getCountLeastMonth(Map<String, Object> queryMap) throws Exception;

    public void addOnlineUser(OnlineUserModel onlineUserModel) throws Exception;

    public void updateOnlineUser(OnlineUserModel onlineUserModel) throws Exception;

    public List<OnlineUserModel> queryAllOnlineUser() throws Exception;

    public void updateOnlineStatus(List<String> onlineUserIds) throws Exception;

    public List<StatisticUserVo> statisticOnlineByYear(@Param("start") String start, @Param("end") String end) throws Exception;
}
