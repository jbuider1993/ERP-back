package com.kunlun.system.dao;

import com.kunlun.system.model.DictionaryModel;
import com.kunlun.system.model.DictionarySubModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IDictionaryDao {
    public List<DictionaryModel> getAllDictionaryItem(Map<String, Object> queryMap) throws Exception;

    public int getDictionaryCount(Map<String, Object> queryMap) throws Exception;

    public List<DictionarySubModel> getAllDictionaryValue(Map<String, Object> queryMap) throws Exception;

    public int getDictionarySubCount(Map<String, Object> queryMap) throws Exception;

    public void insertDictionaryItem(DictionaryModel dictionaryModel) throws Exception;

    public void insertDictionaryValue(DictionarySubModel dictionaryModel) throws Exception;

    public void updateDictionaryItem(DictionaryModel dictionaryModel) throws Exception;

    public void updateDictionaryValue(DictionarySubModel dictionaryModel) throws Exception;

    public void deleteDictionaryItem(String ids) throws Exception;

    public void deleteDictionaryValue(String ids) throws Exception;
}
