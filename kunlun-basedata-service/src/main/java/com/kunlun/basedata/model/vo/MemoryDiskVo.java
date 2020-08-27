package com.kunlun.basedata.model.vo;

public class MemoryDiskVo {

    private String id;

    private String name;

    private String totalCPU;

    private String usedCPU;

    private String cpuRate;

    private String totalDisk;

    private String usedDisk;

    private String diskRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalCPU() {
        return totalCPU;
    }

    public void setTotalCPU(String totalCPU) {
        this.totalCPU = totalCPU;
    }

    public String getUsedCPU() {
        return usedCPU;
    }

    public void setUsedCPU(String usedCPU) {
        this.usedCPU = usedCPU;
    }

    public String getCpuRate() {
        return cpuRate;
    }

    public void setCpuRate(String cpuRate) {
        this.cpuRate = cpuRate;
    }

    public String getTotalDisk() {
        return totalDisk;
    }

    public void setTotalDisk(String totalDisk) {
        this.totalDisk = totalDisk;
    }

    public String getUsedDisk() {
        return usedDisk;
    }

    public void setUsedDisk(String usedDisk) {
        this.usedDisk = usedDisk;
    }

    public String getDiskRate() {
        return diskRate;
    }

    public void setDiskRate(String diskRate) {
        this.diskRate = diskRate;
    }
}
