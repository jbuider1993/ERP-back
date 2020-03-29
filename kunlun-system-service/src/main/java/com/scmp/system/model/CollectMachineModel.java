package com.scmp.system.model;

import java.util.Date;

public class CollectMachineModel {
    private String id;

    private String machineIp;

    private Double totalCPU;

    private Double totalMomery;

    private Double usedCPU;

    private Double usedMomery;

    private Date createDate;

    private String machineId;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public Double getTotalCPU() {
        return totalCPU;
    }

    public void setTotalCPU(Double totalCPU) {
        this.totalCPU = totalCPU;
    }

    public Double getTotalMomery() {
        return totalMomery;
    }

    public void setTotalMomery(Double totalMomery) {
        this.totalMomery = totalMomery;
    }

    public Double getUsedCPU() {
        return usedCPU;
    }

    public void setUsedCPU(Double usedCPU) {
        this.usedCPU = usedCPU;
    }

    public Double getUsedMomery() {
        return usedMomery;
    }

    public void setUsedMomery(Double usedMomery) {
        this.usedMomery = usedMomery;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
