package com.kunlun.system.service.impl;

import com.kunlun.system.config.dataSource.DataSourceType;
import com.kunlun.system.config.dataSource.DbContextHolder;
import com.kunlun.system.dao.IScheduleDao;
import com.kunlun.system.model.SchedulePlanModel;
import com.kunlun.system.service.IScheduleService;
import com.kunlun.system.utils.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScheduleService implements IScheduleService {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IScheduleDao scheduleDao;

    @Override
    public List<SchedulePlanModel> getAllSchedules(SchedulePlanModel scheduleModel) throws Exception {
        DbContextHolder.setDbType(DataSourceType.MASTER.getKey());
        return scheduleDao.getAllSchedules(scheduleModel);
    }

    @Override
    public void addSchedule(SchedulePlanModel scheduleModel) throws Exception {
        DbContextHolder.setDbType(DataSourceType.MASTER.getKey());
        scheduleModel.setId(CommonUtil.generateUUID());
        scheduleModel.setCreateTime(new Date());
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
