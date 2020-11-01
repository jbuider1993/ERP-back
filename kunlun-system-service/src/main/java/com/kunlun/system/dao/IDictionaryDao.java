package com.kunlun.system.dao;

import com.kunlun.system.model.DictionaryItemModel;
import com.kunlun.system.model.DictionaryValueModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IDictionaryDao {
    public List<DictionaryItemModel> getAllDictionaryItem(Map<String, Object> queryMap) throws Exception;

    public int getDictionaryCount(Map<String, Object> queryMap) throws Exception;

    public List<DictionaryValueModel> getAllDictionaryValue(Map<String, Object> queryMap) throws Exception;

    public List<DictionaryValueModel> getValuesByDictCode(String dictCode) throws Exception;

    public int getDictionarySubCount(Map<String, Object> queryMap) throws Exception;

    public void insertDictionaryItem(DictionaryItemModel dictionaryItemModel) throws Exception;

    public void insertDictionaryValue(DictionaryValueModel dictionaryModel) throws Exception;

    public void updateDictionaryItem(DictionaryItemModel dictionaryItemModel) throws Exception;

    public void updateDictionaryValue(DictionaryValueModel dictionaryModel) throws Exception;

    public void deleteDictionaryItem(String ids) throws Exception;

    public void deleteDictionaryValue(String ids) throws Exception;
}
