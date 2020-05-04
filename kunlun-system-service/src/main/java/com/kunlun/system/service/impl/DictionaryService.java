package com.kunlun.system.service.impl;

import com.kunlun.common.model.Page;
import com.kunlun.system.dao.IDictionaryDao;
import com.kunlun.system.model.DictionaryModel;
import com.kunlun.system.model.DictionarySubModel;
import com.kunlun.system.service.IDictionaryService;
import com.kunlun.system.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DictionaryService implements IDictionaryService {

    @Autowired
    private IDictionaryDao dictionaryDao;

    @Override
    public Page<DictionaryModel> getAllDictionaryItem(DictionaryModel dictionaryModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(dictionaryModel, startIndex, pageSize);

        int count = dictionaryDao.getDictionaryCount(queryMap);
        List<DictionaryModel> dictionaryList = dictionaryDao.getAllDictionaryItem(queryMap);

        Page<DictionaryModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(dictionaryList);
        return page;
    }

    @Override
    public Page<DictionarySubModel> getAllDictionaryValue(DictionarySubModel dictionaryModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(dictionaryModel, startIndex, pageSize);

        int count = dictionaryDao.getDictionarySubCount(queryMap);
        List<DictionarySubModel> dictionaryList = dictionaryDao.getAllDictionaryValue(queryMap);

        Page<DictionarySubModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(dictionaryList);
        return page;
    }

    @Override
    public void insertDictionaryItem(DictionaryModel dictionaryModel) throws Exception {
        dictionaryModel.setId(CommonUtil.generateUUID());
        dictionaryModel.setCreateTime(new Date());
        dictionaryModel.setModifiedTime(new Date());
        dictionaryDao.insertDictionaryItem(dictionaryModel);
    }

    @Override
    public void insertDictionaryValue(DictionarySubModel dictionaryModel) throws Exception {
        dictionaryModel.setId(CommonUtil.generateUUID());
        dictionaryModel.setCreateTime(new Date());
        dictionaryModel.setModifiedTime(new Date());
        dictionaryDao.insertDictionaryValue(dictionaryModel);
    }

    @Override
    public void updateDictionaryItem(DictionaryModel dictionaryModel) throws Exception {
        dictionaryModel.setModifiedTime(new Date());
        dictionaryDao.updateDictionaryItem(dictionaryModel);
    }

    @Override
    public void updateDictionaryValue(DictionarySubModel dictionaryModel) throws Exception {
        dictionaryModel.setModifiedTime(new Date());
        dictionaryDao.updateDictionaryValue(dictionaryModel);
    }

    @Override
    public void deleteDictionaryItem(String ids) throws Exception {
        dictionaryDao.deleteDictionaryItem(ids);
    }

    @Override
    public void deleteDictionaryValue(String ids) throws Exception {
        dictionaryDao.deleteDictionaryValue(ids);
    }
}
