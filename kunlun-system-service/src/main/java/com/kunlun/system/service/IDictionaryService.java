package com.kunlun.system.service;

import com.kunlun.common.model.Page;
import com.kunlun.system.model.DictionaryModel;
import com.kunlun.system.model.DictionarySubModel;

public interface IDictionaryService {
    public Page<DictionaryModel> getAllDictionary(DictionaryModel dictionaryModel, int currentPage, int pageSize) throws Exception;

    public Page<DictionarySubModel> getAllDictionarySub(DictionarySubModel dictionaryModel, int currentPage, int pageSize) throws Exception;
}
