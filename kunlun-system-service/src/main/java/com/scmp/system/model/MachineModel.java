package com.scmp.system.model;

import java.util.Date;

public class MachineModel {
    private String id;

    private String machineName;

    private String machineIp;

    private String virtualIp;

    private Integer port;

    private Double totalCPU;

    private Double totalMomery;

    private Double usedCPU;

    private Double usedMomery;

    private boolean stopFlag;

    private Date createDate;

    private Date modifiedDate;

    public boolean isStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public String getVirtualIp() {
        return virtualIp;
    }

    public void setVirtualIp(String virtualIp) {
        this.virtualIp = virtualIp;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
