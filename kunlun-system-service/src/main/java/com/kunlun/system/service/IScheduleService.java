package com.kunlun.system.service;

import com.kunlun.common.model.Page;
import com.kunlun.system.model.MessageNoticeModel;
import com.kunlun.system.model.SchedulePlanModel;

import java.util.List;
import java.util.Map;

public interface IScheduleService {
    public List<SchedulePlanModel> getAllSchedules(SchedulePlanModel scheduleModel) throws Exception;

    public void addSchedule(SchedulePlanModel scheduleModel) throws Exception;

    public void updateSchedule(SchedulePlanModel scheduleModel) throws Exception;

    public void deleteSchedule(List<String> ids) throws Exception;
}
