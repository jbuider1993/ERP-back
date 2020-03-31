package com.kunlun.system.service.impl;

import com.kunlun.system.dao.IProcessDao;
import com.kunlun.system.model.ProcessModel;
import com.kunlun.system.utils.CommonUtil;
import com.scmp.common.model.Page;
import com.kunlun.system.config.dataSource.DataSourceType;
import com.kunlun.system.config.dataSource.DbContextHolder;
import com.kunlun.system.service.IProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("processService")
@Transactional
public class ProcessService implements IProcessService {

    @Autowired
    private IProcessDao processDao;

    @Override
    public Page getAllProcess(ProcessModel model, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(model, startIndex, pageSize);

        // 切换数据库
        DbContextHolder.setDbType(DataSourceType.ACTIVITI.getKey());

        List<ProcessModel> processes = new ArrayList<>();
        List<ProcessModel> processList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(model.getDataType())) {
            processList = processDao.getAllTodo(queryMap);
        } else {
            processList = processDao.getAllProcess(queryMap);
        }
        Map<String, List<ProcessModel>> modelMap = processList.stream().collect(Collectors.groupingBy(ProcessModel::getId));
        for (Map.Entry map : modelMap.entrySet()) {
            List<ProcessModel> values = (List<ProcessModel>) map.getValue();
            ProcessModel first = values.get(0);
            ProcessModel last = values.get(values.size() - 1);

            ProcessModel obj = new ProcessModel();
            obj.setId((String) map.getKey());
            obj.setKey(first.getKey());
            obj.setModelId(first.getModelId());
            obj.setModelName(first.getModelName());
            obj.setProcessName(first.getProcessName());
            obj.setDeploymentId(first.getDeploymentId());
            obj.setCurrentExecuteKey(last.getCurrentExecuteKey());
            obj.setCurrentExecuteName(last.getCurrentExecuteName());
            obj.setNextExecuteKey(first.getCurrentExecuteKey());
            obj.setNextExecuteName(first.getCurrentExecuteName());
            obj.setProcessInstanceId(first.getProcessInstanceId());
            obj.setProcessStatus(first.getProcessStatus());
            obj.setStartTime(last.getStartTime());
            obj.setEndTime(first.getEndTime());
            processes.add(obj);
        }

        int count = 0;
        if (!ObjectUtils.isEmpty(model.getDataType())) {
            count = processDao.getTodoCount(queryMap);
        } else {
            count = processDao.getProcessCount(queryMap);
        }

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(processes);
        return page;
    }
}
