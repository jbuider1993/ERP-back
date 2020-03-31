package com.kunlun.system.service.impl;

import com.kunlun.system.dao.IOperateLogDao;
import com.kunlun.system.utils.CommonUtil;
import com.scmp.common.model.OperatorLogModel;
import com.scmp.common.model.Page;
import com.kunlun.system.service.IOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
