package com.kunlun.system.service.impl;

import com.kunlun.system.dao.IScheduleDao;
import com.kunlun.system.model.SchedulePlanModel;
import com.kunlun.system.service.IScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ScheduleService implements IScheduleService {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IScheduleDao scheduleDao;

    @Override
    public List<SchedulePlanModel> getAllSchedules(SchedulePlanModel scheduleModel) throws Exception {
        return scheduleDao.getAllSchedules(scheduleModel);
    }

    @Override
    public void addSchedule(SchedulePlanModel scheduleModel) throws Exception {
        scheduleDao.addSchedule(scheduleModel);
    }

    @Override
    public void updateSchedule(SchedulePlanModel scheduleModel) throws Exception {
        scheduleDao.updateSchedule(scheduleModel);
    }

    @Override
    public void deleteSchedule(List<String> ids) throws Exception {
        scheduleDao.deleteSchedule(ids);
    }
}
