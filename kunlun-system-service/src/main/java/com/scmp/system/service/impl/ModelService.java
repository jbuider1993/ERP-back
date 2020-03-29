package com.scmp.system.service.impl;

import com.scmp.common.model.Page;
import com.scmp.system.config.dataSource.DataSourceType;
import com.scmp.system.config.dataSource.DbContextHolder;
import com.scmp.system.dao.IModelDao;
import com.scmp.system.model.ModelModel;
import com.scmp.system.service.IModelService;
import com.scmp.system.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service("modelService")
@Transactional
public class ModelService implements IModelService {

    @Autowired
    private IModelDao modelDao;

    @Override
    public Page getAllProcess(ModelModel model, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(model, startIndex, pageSize);

        // 切换数据库
        DbContextHolder.setDbType(DataSourceType.ACTIVITI.getKey());

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(modelDao.getProcessesCount(queryMap));
        page.setRecords(modelDao.getAllProcess(queryMap));
        return page;
    }

    @Override
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, String type) throws Exception {

    }

    @Override
    public boolean batchDeleteModel(String[] processIds) throws Exception {
        return modelDao.batchDeleteModel(processIds);
    }
}
