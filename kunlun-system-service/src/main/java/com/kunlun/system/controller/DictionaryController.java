package com.kunlun.system.controller;

import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.model.DictionaryModel;
import com.kunlun.system.model.DictionarySubModel;
import com.kunlun.system.service.IDictionaryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictionaryController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IDictionaryService dictionaryService;

    @RequestMapping(value = "/getAllDictionary", method = RequestMethod.GET)
    public Object getAllDictionary(DictionaryModel dictionaryModel, int currentPage, int pageSize) {
        try {
            Page<DictionaryModel> pages = dictionaryService.getAllDictionary(dictionaryModel, currentPage, pageSize);
            return ResponseUtil.successResponse(pages);
        } catch (Exception e) {
            log.error("DictionaryController getAllDictionary Error: ", e);
            return ResponseUtil.failedResponse("查询字典数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/getAllDictionarySub", method = RequestMethod.GET)
    public Object getAllDictionarySub(DictionarySubModel dictionaryModel, int currentPage, int pageSize) {
        try {
            Page<DictionarySubModel> pages = dictionaryService.getAllDictionarySub(dictionaryModel, currentPage, pageSize);
            return ResponseUtil.successResponse(pages);
        } catch (Exception e) {
            log.error("DictionaryController getAllDictionary Error: ", e);
            return ResponseUtil.failedResponse("查询字典数据失败！", e.getMessage());
        }
    }
}
