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

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DictionaryService implements IDictionaryService {

    @Autowired
    private IDictionaryDao dictionaryDao;

    @Override
    public Page<DictionaryModel> getAllDictionary(DictionaryModel dictionaryModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(dictionaryModel, startIndex, pageSize);

        int count = dictionaryDao.getDictionaryCount(queryMap);
        List<DictionaryModel> dictionaryList = dictionaryDao.getAllDictionary(queryMap);

        Page<DictionaryModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(dictionaryList);
        return page;
    }

    @Override
    public Page<DictionarySubModel> getAllDictionarySub(DictionarySubModel dictionaryModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(dictionaryModel, startIndex, pageSize);

        int count = dictionaryDao.getDictionarySubCount(queryMap);
        List<DictionarySubModel> dictionaryList = dictionaryDao.getAllDictionarySub(queryMap);

        Page<DictionarySubModel> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setRecords(dictionaryList);
        return page;
    }
}
