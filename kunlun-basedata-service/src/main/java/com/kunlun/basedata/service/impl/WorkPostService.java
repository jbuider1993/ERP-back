package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.dao.IWorkPostDao;
import com.kunlun.basedata.model.WorkPostModel;
import com.kunlun.basedata.service.IWorkPostService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.common.model.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 岗位业务Service类
 */
@Service
@Transactional
public class WorkPostService implements IWorkPostService {

    private final Logger logger = LogManager.getLogger();

    @Autowired
    private IWorkPostDao workPostDao;

    @Override
    public Page getAllWorkPost(WorkPostModel workPostModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(workPostModel, startIndex, pageSize);
        int count = workPostDao.getWorkPostCount(queryMap);
        List<WorkPostModel> allWorkPost = workPostDao.getAllWorkPost(queryMap);

        Page<WorkPostModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(allWorkPost);
        return page;
    }

    @Override
    public void addWorkPost(WorkPostModel workPostModel) throws Exception {
        workPostModel.setId(CommonUtil.generateUUID());
        workPostDao.addWorkPost(workPostModel);
    }

    @Override
    public void updateWorkPost(WorkPostModel workPostModel) throws Exception {
        workPostDao.updateWorkPost(workPostModel);
    }

    @Override
    public void deleteWorkPost(List<String> list) throws Exception {
        workPostDao.deleteWorkPost(list);
    }
}
