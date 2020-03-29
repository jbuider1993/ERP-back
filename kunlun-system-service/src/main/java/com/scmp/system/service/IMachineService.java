package com.scmp.system.service;

import com.scmp.system.model.MachineModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IMachineService {
    public List<MachineModel> getAllMachine(MachineModel machineModel, int currentPage, int pageSize) throws Exception;

    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String type) throws Exception;
}
