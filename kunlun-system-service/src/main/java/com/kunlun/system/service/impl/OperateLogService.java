package com.kunlun.system.service.impl;

import com.kunlun.common.model.OperatorLogModel;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ExcelUtil;
import com.kunlun.system.config.dataSource.DataSourceType;
import com.kunlun.system.config.dataSource.DbContextHolder;
import com.kunlun.system.dao.IOperateLogDao;
import com.kunlun.system.utils.CommonUtil;
import com.kunlun.system.service.IOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("operateLogService")
@Transactional
public class OperateLogService implements IOperateLogService {

    @Autowired
    private IOperateLogDao operateLogDao;

    @Override
    public Page getAllOperateLog(OperatorLogModel logModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(logModel, startIndex, pageSize);

        DbContextHolder.setDbType(DataSourceType.MASTER.getKey());

        List<OperatorLogModel> logList = operateLogDao.getAllOperateLog(queryMap);
        int count = operateLogDao.getOperateLogCount(queryMap);

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(logList);
        return page;
    }

    @Override
    public int getOperateLogCount(Map<String, Object> queryMap) throws Exception {
        return operateLogDao.getOperateLogCount(queryMap);
    }

    @Override
    public void insertOperateLog(OperatorLogModel logModel) throws Exception {
        operateLogDao.insertOperateLog(logModel);
    }

    @Override
    public void updateOperateLog(OperatorLogModel logModel) throws Exception {
        operateLogDao.updateOperateLog(logModel);
    }

    @Override
    public void exportOperateLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(new OperatorLogModel(), 0, 99999);
        List<OperatorLogModel> logList = operateLogDao.getAllOperateLog(queryMap);
        String[] headerNames = new String[]{"IP??????", "?????????", "????????????", "????????????", "??????URL", "????????????", "????????????", "????????????", "???????????????", "??????????????????", "???????????????", "????????????", "????????????", "????????????", "????????????", "????????????"};
        String[] fieldNames = new String[]{"ip", "userName", "operatorType", "operateDescription", "requestUrl", "protocal", "params", "style", "serviceName", "port", "threadName", "clzName", "methodName", "exceptionInfo", "status", "operateTime"};
        int[] lineWidths = new int[]{35, 35, 30, 50, 80, 30, 80, 30, 60, 35, 60, 80, 50, 60, 30, 60};

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dataSource", logList);
        paramMap.put("sheetName", "????????????");
        paramMap.put("headerNames", headerNames);
        paramMap.put("fieldNames", fieldNames);
        paramMap.put("lineWidths", lineWidths);
        ExcelUtil.exportExcel(request, response, OperatorLogModel.class, paramMap);
    }
}
