package com.kunlun.system.service;

import com.kunlun.common.model.Page;
import com.kunlun.system.model.DictionaryModel;
import com.kunlun.system.model.DictionarySubModel;

public interface IDictionaryService {
    public Page<DictionaryModel> getAllDictionaryItem(DictionaryModel dictionaryModel, int currentPage, int pageSize) throws Exception;

    public Page<DictionarySubModel> getAllDictionaryValue(DictionarySubModel dictionaryModel, int currentPage, int pageSize) throws Exception;

    public void insertDictionaryItem(DictionaryModel dictionaryModel) throws Exception;

    public void insertDictionaryValue(DictionarySubModel dictionaryModel) throws Exception;

    public void updateDictionaryItem(DictionaryModel dictionaryModel) throws Exception;

    public void updateDictionaryValue(DictionarySubModel dictionaryModel) throws Exception;

    public void deleteDictionaryItem(String ids) throws Exception;

    public void deleteDictionaryValue(String ids) throws Exception;
}
