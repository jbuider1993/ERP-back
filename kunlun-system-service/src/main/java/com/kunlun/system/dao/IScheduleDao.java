package com.kunlun.system.dao;

import com.kunlun.system.model.SchedulePlanModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScheduleDao {
    public List<SchedulePlanModel> getAllSchedules(SchedulePlanModel scheduleModel) throws Exception;

    public int getScheduleeCount(SchedulePlanModel scheduleModel) throws Exception;

    public void addSchedule(SchedulePlanModel scheduleModel) throws Exception;

    public void updateSchedule(SchedulePlanModel scheduleModel) throws Exception;

    public void deleteSchedule(List<String> ids) throws Exception;
}
