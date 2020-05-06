package com.kunlun.system.service;

import com.kunlun.common.model.Page;
import com.kunlun.system.model.DictionaryItemModel;
import com.kunlun.system.model.DictionaryValueModel;

public interface IDictionaryService {
    public Page<DictionaryItemModel> getAllDictionaryItem(DictionaryItemModel dictionaryItemModel, int currentPage, int pageSize) throws Exception;

    public Page<DictionaryValueModel> getAllDictionaryValue(DictionaryValueModel dictionaryModel, int currentPage, int pageSize) throws Exception;

    public void insertDictionaryItem(DictionaryItemModel dictionaryItemModel) throws Exception;

    public void insertDictionaryValue(DictionaryValueModel dictionaryModel) throws Exception;

    public void updateDictionaryItem(DictionaryItemModel dictionaryItemModel) throws Exception;

    public void updateDictionaryValue(DictionaryValueModel dictionaryModel) throws Exception;

    public void deleteDictionaryItem(String ids) throws Exception;

    public void deleteDictionaryValue(String ids) throws Exception;
}
