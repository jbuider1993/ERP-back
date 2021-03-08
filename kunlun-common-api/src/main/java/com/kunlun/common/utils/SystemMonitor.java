package com.kunlun.common.utils;

import com.kunlun.common.model.SystemDataModel;
import com.sun.management.OperatingSystemMXBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取系统信息工具类
 */
public class SystemMonitor {

    private static Logger log = LogManager.getLogger();

    public static SystemDataModel collect() {
        SystemDataModel systemModel = new SystemDataModel();
        try {
            // 操作系统
            fetchSystemData(systemModel);

            // 磁盘使用情况
            fetchDiskData(systemModel);

            // Memory使用情况
            fetchMemoryData(systemModel);
        } catch (Exception e) {
            log.error("get system information error", e);
        }
        return systemModel;
    }

    private static void fetchSystemData(SystemDataModel systemModel) {
        String osName = System.getProperty("os.name");
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()));
        log.info("操作系统: " + osName + " 程序启动时间: " + startTime);
        systemModel.setOsName(osName);
        systemModel.setStartTime(startTime);
    }

    private static void fetchMemoryData(SystemDataModel systemModel) {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String totalMemory = new DecimalFormat("#.##").format(osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024);
        String usedMemory = new DecimalFormat("#.##").format((osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024);
        String rate = new BigDecimal(usedMemory).divide(new BigDecimal(totalMemory), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).toString();
        log.info("总物理内存大小: " + totalMemory + "M" + " 已使用的物理内存: " + usedMemory + "M" + " 物理内存使用率 " + rate + "%");
        systemModel.setMaxMemory(totalMemory);
        systemModel.setUsedMemory(usedMemory);
        systemModel.setUsedMemoryRate(rate);
    }

    private static void fetchDiskData(SystemDataModel systemModel) {
        String totalDisk = "0", usedDisk = "0";
        File[] files = File.listRoots();
        for (File file : files) {
            String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024);
            String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024);
            String un = new DecimalFormat("#.#").format(file.getUsableSpace() * 1.0 / 1024 / 1024);

            usedDisk = new BigDecimal(usedDisk).add(new BigDecimal(total).subtract(new BigDecimal(free))).toString();
            totalDisk = new BigDecimal(totalDisk).add(new BigDecimal(total)).toString();
        }
        BigDecimal temp = new BigDecimal(usedDisk).divide(new BigDecimal(totalDisk), 2, BigDecimal.ROUND_HALF_UP);
        String rate = temp.multiply(new BigDecimal(100)).toString();
        log.info("磁盘总大小： " + totalDisk + "M" + " 磁盘已使用大小：" + usedDisk + "M" + " 磁盘使用率大小： " + rate + "%");
        systemModel.setMaxDisk(totalDisk);
        systemModel.setUsedDisk(usedDisk);
        systemModel.setUsedDiskRate(rate);
    }
}
