package com.kunlun.system.task;

import com.kunlun.system.model.DictionaryValueModel;
import com.kunlun.system.service.IDictionaryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ScheduleTaskService {

    private static Logger log = LogManager.getLogger();

    private ScheduledExecutorService executorPool = Executors.newScheduledThreadPool(1);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IDictionaryService dictionaryService;

    public void startMonitor() {
        log.info("TaskService startMonitor start");
        try {
            List<DictionaryValueModel> dictValues = dictionaryService.getValuesByDictCode("service");
            ScheduleMonitorTask monitorTask = new ScheduleMonitorTask(restTemplate, dictValues);
            executorPool.scheduleAtFixedRate(monitorTask, 1, 60 * 5, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("ScheduleTaskService Error: ", e);
        }
        log.info("TaskService startMonitor end");
    }
}
