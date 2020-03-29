package com.scmp.system.controller;

import com.scmp.common.utils.ResponseUtil;
import com.scmp.system.model.MachineModel;
import com.scmp.system.service.IMachineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/machine")
public class MachineController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IMachineService machineService;

    @RequestMapping(value = "/getAllMachine", method = RequestMethod.GET)
    public Object getAllMachine(MachineModel machineModel, int currentPage, int pageSize) {
        try {
            List<MachineModel> list = machineService.getAllMachine(machineModel, currentPage, pageSize);
            return ResponseUtil.successResponse(list);
        } catch (Exception e) {
            log.error("MachineController getAllMachine Error: ", e);
            return ResponseUtil.failedResponse("查询虚拟机容器列表数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    public Object downloadTemplate(HttpServletRequest request, HttpServletResponse response, String type) {
        try {
            machineService.downloadTemplate(request, response, type);
        } catch (Exception e) {
            log.error("MachineController getAllMachine Error: ", e);
            return ResponseUtil.failedResponse("查询虚拟机容器列表数据失败！", e.getMessage());
        }
        return null;
    }
}
