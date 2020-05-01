package com.kunlun.system.dao;

import com.kunlun.system.model.DictionaryModel;
import com.kunlun.system.model.DictionarySubModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IDictionaryDao {
    public List<DictionaryModel> getAllDictionary(Map<String, Object> queryMap) throws Exception;

    public int getDictionaryCount(Map<String, Object> queryMap) throws Exception;

    public List<DictionarySubModel> getAllDictionarySub(Map<String, Object> queryMap) throws Exception;

    public int getDictionarySubCount(Map<String, Object> queryMap) throws Exception;
}
