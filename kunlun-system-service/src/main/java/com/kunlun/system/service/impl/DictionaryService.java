package com.kunlun.system.service.impl;

import com.kunlun.common.model.Page;
import com.kunlun.system.dao.IDictionaryDao;
import com.kunlun.system.model.DictionaryItemModel;
import com.kunlun.system.model.DictionaryValueModel;
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
    public Page<DictionaryItemModel> getAllDictionaryItem(DictionaryItemModel dictionaryItemModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(dictionaryItemModel, startIndex, pageSize);

        int count = dictionaryDao.getDictionaryCount(queryMap);
        List<DictionaryItemModel> dictionaryList = dictionaryDao.getAllDictionaryItem(queryMap);

        Page<DictionaryItemModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(dictionaryList);
        return page;
    }

    @Override
    public Page<DictionaryValueModel> getAllDictionaryValue(DictionaryValueModel dictionaryModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(dictionaryModel, startIndex, pageSize);

        int count = dictionaryDao.getDictionarySubCount(queryMap);
        List<DictionaryValueModel> dictionaryList = dictionaryDao.getAllDictionaryValue(queryMap);

        Page<DictionaryValueModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(dictionaryList);
        return page;
    }

    @Override
    public void insertDictionaryItem(DictionaryItemModel dictionaryItemModel) throws Exception {
        dictionaryItemModel.setId(CommonUtil.generateUUID());
        dictionaryItemModel.setCreateTime(new Date());
        dictionaryItemModel.setModifiedTime(new Date());
        dictionaryDao.insertDictionaryItem(dictionaryItemModel);
    }

    @Override
    public void insertDictionaryValue(DictionaryValueModel dictionaryModel) throws Exception {
        dictionaryModel.setId(CommonUtil.generateUUID());
        dictionaryModel.setCreateTime(new Date());
        dictionaryModel.setModifiedTime(new Date());
        dictionaryDao.insertDictionaryValue(dictionaryModel);
    }

    @Override
    public void updateDictionaryItem(DictionaryItemModel dictionaryItemModel) throws Exception {
        dictionaryItemModel.setModifiedTime(new Date());
        dictionaryDao.updateDictionaryItem(dictionaryItemModel);
    }

    @Override
    public void updateDictionaryValue(DictionaryValueModel dictionaryModel) throws Exception {
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
