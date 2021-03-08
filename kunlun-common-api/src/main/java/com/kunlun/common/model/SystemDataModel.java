package com.kunlun.common.model;

/**
 * 系统信息模型
 */
public class SystemDataModel {

    private String id;

    private String osName;

    private String system;

    private String startTime;

    private String cpuCore;

    private String usedCpuRate;

    private String maxMemory;

    private String usedMemory;

    private String usedMemoryRate;

    private String maxDisk;

    private String usedDisk;

    private String usedDiskRate;

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

    public String getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(String maxMemory) {
        this.maxMemory = maxMemory;
    }

    public String getMaxDisk() {
        return maxDisk;
    }

    public void setMaxDisk(String maxDisk) {
        this.maxDisk = maxDisk;
    }

    public String getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(String cpuCore) {
        this.cpuCore = cpuCore;
    }

    public String getUsedCpuRate() {
        return usedCpuRate;
    }

    public void setUsedCpuRate(String usedCpuRate) {
        this.usedCpuRate = usedCpuRate;
    }

    public String getUsedMemoryRate() {
        return usedMemoryRate;
    }

    public void setUsedMemoryRate(String usedMemoryRate) {
        this.usedMemoryRate = usedMemoryRate;
    }

    public String getUsedDiskRate() {
        return usedDiskRate;
    }

    public void setUsedDiskRate(String usedDiskRate) {
        this.usedDiskRate = usedDiskRate;
    }

    public String getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory;
    }

    public String getUsedDisk() {
        return usedDisk;
    }

    public void setUsedDisk(String usedDisk) {
        this.usedDisk = usedDisk;
    }
}
