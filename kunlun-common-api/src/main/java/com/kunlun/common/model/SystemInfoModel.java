package com.kunlun.common.model;

/**
 * 系统信息模型
 */
public class SystemInfoModel {

    private String id;

    private String osName;

    private String system;

    private String startTime;

    private int core;

    private long maxCpu;

    private long freeCpu;

    private String cpuUsedRate;

    private long maxMemory;

    private long freeMemory;

    private String memoryUsedRate;

    private long maxDisk;

    private long freeDisk;

    private String diskUsedRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public long getMaxCpu() {
        return maxCpu;
    }

    public void setMaxCpu(long maxCpu) {
        this.maxCpu = maxCpu;
    }

    public long getFreeCpu() {
        return freeCpu;
    }

    public void setFreeCpu(long freeCpu) {
        this.freeCpu = freeCpu;
    }

    public String getCpuUsedRate() {
        return cpuUsedRate;
    }

    public void setCpuUsedRate(String cpuUsedRate) {
        this.cpuUsedRate = cpuUsedRate;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public String getMemoryUsedRate() {
        return memoryUsedRate;
    }

    public void setMemoryUsedRate(String memoryUsedRate) {
        this.memoryUsedRate = memoryUsedRate;
    }

    public long getMaxDisk() {
        return maxDisk;
    }

    public void setMaxDisk(long maxDisk) {
        this.maxDisk = maxDisk;
    }

    public long getFreeDisk() {
        return freeDisk;
    }

    public void setFreeDisk(long freeDisk) {
        this.freeDisk = freeDisk;
    }

    public String getDiskUsedRate() {
        return diskUsedRate;
    }

    public void setDiskUsedRate(String diskUsedRate) {
        this.diskUsedRate = diskUsedRate;
    }
}
