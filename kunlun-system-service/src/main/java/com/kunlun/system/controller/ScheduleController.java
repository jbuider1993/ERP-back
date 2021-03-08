package com.kunlun.system.controller;

import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.model.SchedulePlanModel;
import com.kunlun.system.service.IScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IScheduleService scheduleService;

    @RequestMapping(value = "/getAllSchedules", method = RequestMethod.GET)
    public Object getAllSchedules(SchedulePlanModel scheduleModel) {
        try {
            List<SchedulePlanModel> schedulePlanModels = scheduleService.getAllSchedules(scheduleModel);
            return ResponseUtil.successResponse(schedulePlanModels);
        } catch (Exception e) {
            log.error("ScheduleController getAllSchedules Error: ", e);
            return ResponseUtil.failedResponse("查询所有事项日程失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
    public Object addSchedule(SchedulePlanModel scheduleModel) {
        try {
            scheduleService.addSchedule(scheduleModel);
            return ResponseUtil.successResponse(scheduleModel);
        } catch (Exception e) {
            log.error("ScheduleController addSchedule Error: ", e);
            return ResponseUtil.failedResponse("新增事项日程失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
    public Object updateSchedule(SchedulePlanModel scheduleModel) {
        try {
            scheduleService.updateSchedule(scheduleModel);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("ScheduleController updateSchedule Error: ", e);
            return ResponseUtil.failedResponse("修改事项日程失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.POST)
    public Object deleteSchedule(String ids) {
        try {
            List<String> idList = Arrays.asList(ids.split(","));
            scheduleService.deleteSchedule(idList);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("ScheduleController deleteSchedule Error: ", e);
            return ResponseUtil.failedResponse("删除事项日程失败！", e.getMessage());
        }
    }
}
