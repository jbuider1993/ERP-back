package com.kunlun.common.utils;

import com.kunlun.common.model.SystemInfoModel;
import com.sun.management.OperatingSystemMXBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

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

    public static SystemInfoModel init() {
        SystemInfoModel systemInfoModel = new SystemInfoModel();
        try {
            // 操作系统
            fetchSystemInfo(systemInfoModel);

            // CPU使用情况
            SystemInfo systemInfo = new SystemInfo();
            fetchCPUInfo(systemInfo, systemInfoModel);

            // 磁盘使用情况
            fetchDIskInfo(systemInfoModel);

            // Memory使用情况
            fetchMemoryInfo(systemInfoModel);
        } catch (Exception e) {
            log.error("get system information error", e);
        }
        return systemInfoModel;
    }

    private static void fetchSystemInfo(SystemInfoModel systemInfoModel) {
        String osName = System.getProperty("os.name");
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()));
        System.out.println("操作系统:" + osName);
        System.out.println("程序启动时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));
        systemInfoModel.setOsName(osName);
        systemInfoModel.setStartTime(startTime);
    }

    private static void fetchMemoryInfo(SystemInfoModel systemInfoModel) {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String totalMemory = new DecimalFormat("#.##").format(osmxb.getTotalPhysicalMemorySize() / 1024.0 / 1024);
        String usedMemoryA = new DecimalFormat("#.##").format((osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / 1024.0 / 1024);
        System.out.println("总物理内存大小 ===>>> " + totalMemory + "M");
        System.out.println("已使用的物理内存 ===>>> " + usedMemoryA + "M");
        System.out.println("物理内存使用率 ===>>> " + new BigDecimal(usedMemoryA).divide(new BigDecimal(totalMemory), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).toString() + "%");
    }

    private static void fetchDIskInfo(SystemInfoModel systemInfoModel) {
        String totalDisk = "0", usedDisk = "0";
        File[] files = File.listRoots();
        for (File file : files) {
            String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024);
            String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024);
            String un = new DecimalFormat("#.#").format(file.getUsableSpace() * 1.0 / 1024 / 1024);

            usedDisk = new BigDecimal(usedDisk).add(new BigDecimal(total).subtract(new BigDecimal(free))).toString();
            totalDisk = new BigDecimal(totalDisk).add(new BigDecimal(total)).toString();
        }
        System.out.println("磁盘总大小 ===>>> " + totalDisk + "M");
        System.out.println("磁盘已使用大小 ===>>> " + usedDisk + "M");
        BigDecimal temp = new BigDecimal(usedDisk).divide(new BigDecimal(totalDisk), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("磁盘使用率大小 ===>>> " + temp.multiply(new BigDecimal(100)).toString() + "%");
    }

    private static void fetchCPUInfo(SystemInfo systemInfo, SystemInfoModel model) {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

        String usedSystem = new DecimalFormat("#.##").format(cSys * 1.0 / totalCpu);
        String usedUser = new DecimalFormat("#.##").format(user * 1.0 / totalCpu);
        BigDecimal totalUse = new BigDecimal(usedSystem).add(new BigDecimal(usedUser)).multiply(new BigDecimal(100));
        System.out.println("CPU核数 ===>>> " + processor.getLogicalProcessorCount());
        System.out.println("CPU使用率 ===>>> " + totalUse.toString());
        System.out.println("CPU空闲率 ===>>> " + new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));
        model.setCore(processor.getLogicalProcessorCount());
    }
}
