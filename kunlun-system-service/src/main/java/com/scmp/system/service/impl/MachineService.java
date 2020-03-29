package com.scmp.system.service.impl;

import com.scmp.system.dao.IMachineDao;
import com.scmp.system.model.MachineModel;
import com.scmp.system.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MachineService implements IMachineService {

    @Autowired
    private IMachineDao machineDao;

    @Override
    public List<MachineModel> getAllMachine(MachineModel machineModel, int currentPage, int pageSize) throws Exception {
        return machineDao.getAllMachine(machineModel, currentPage, pageSize);
    }

    @Override
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String type) throws Exception{
        String templatePath = null;
        if ("machine".equals(type)) {
            templatePath = "D:\\template\\machine_template.xlsx";
            exportTemplate(response, templatePath);
        } else {
            templatePath = System.getProperty("user.dir");
            exportTemplate(response, templatePath);
        }
    }

    private void exportTemplate(HttpServletResponse response, String templatePath) throws Exception {
        OutputStream os = response.getOutputStream();
        InputStream is = new FileInputStream(templatePath);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String fileName = dateFormat.format(new Date()) + ".xlsx";

        response.addHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "utf-8"));
        byte[] bytes = new byte[4096];
        int size = is.read(bytes);
        while (size > 0) {
            os.write(bytes, 0, size);
            size = is.read(bytes);
        }
        os.close();
        is.close();
    }
}
