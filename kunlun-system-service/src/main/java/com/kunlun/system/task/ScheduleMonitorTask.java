package com.kunlun.system.task;

import com.kunlun.common.model.SystemDataModel;
import com.kunlun.system.model.DictionaryValueModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 系统信息定时收集任务
 *
 * CPU、Disk、Memory
 */
public class ScheduleMonitorTask implements Runnable {

    private static Logger log = LogManager.getLogger();

    private static final String SUFFIX = "/home/collect";

    private RestTemplate restTemplate;

    private List<DictionaryValueModel> dictValues;

    public ScheduleMonitorTask(RestTemplate restTemplate, List<DictionaryValueModel> dictValues) {
        this.restTemplate = restTemplate;
        this.dictValues = dictValues;
    }

    @Override
    public void run() {
        try {
            for (DictionaryValueModel dictValue : dictValues) {
                log.info("monitoring service ===>>> " + dictValue.getDictSubCode());
                String url = "http://localhost:8015/" + dictValue.getDictSubCode() + SUFFIX;
                SystemDataModel systemData = restTemplate.getForObject(url, SystemDataModel.class);
                log.info(dictValue.getDictSubCode() + " " + systemData.getOsName() + " " + systemData.getCpuCore());
            }
        } catch (RestClientException e) {
            log.error("ScheduleMonitorTask Error: ", e);
        }
    }
}
