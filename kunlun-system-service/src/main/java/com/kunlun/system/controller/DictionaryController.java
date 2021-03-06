package com.kunlun.system.controller;

import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.model.DictionaryItemModel;
import com.kunlun.system.model.DictionaryValueModel;
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

    @RequestMapping(value = "/getAllDictionaryItem", method = RequestMethod.GET)
    public Object getAllDictionaryItem(DictionaryItemModel dictionaryItemModel, int currentPage, int pageSize) {
        try {
            Page<DictionaryItemModel> pages = dictionaryService.getAllDictionaryItem(dictionaryItemModel, currentPage, pageSize);
            return ResponseUtil.successResponse(pages);
        } catch (Exception e) {
            log.error("DictionaryController getAllDictionaryItem Error: ", e);
            return ResponseUtil.failedResponse("查询字典数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/getAllDictionaryValue", method = RequestMethod.GET)
    public Object getAllDictionaryValue(DictionaryValueModel dictionaryModel, int currentPage, int pageSize) {
        try {
            Page<DictionaryValueModel> pages = dictionaryService.getAllDictionaryValue(dictionaryModel, currentPage, pageSize);
            return ResponseUtil.successResponse(pages);
        } catch (Exception e) {
            log.error("DictionaryController getAllDictionaryValue Error: ", e);
            return ResponseUtil.failedResponse("查询字典数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/insertDictionaryItem", method = RequestMethod.POST)
    public Object insertDictionaryItem(DictionaryItemModel dictionaryItemModel) {
        try {
            dictionaryService.insertDictionaryItem(dictionaryItemModel);
            return ResponseUtil.successResponse("插入字典项数据成功！");
        } catch (Exception e) {
            log.error("DictionaryController insertDictionaryItem Error: ", e);
            return ResponseUtil.failedResponse("插入字典项数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/insertDictionaryValue", method = RequestMethod.POST)
    public Object insertDictionaryValue(DictionaryValueModel dictionaryModel) {
        try {
            dictionaryService.insertDictionaryValue(dictionaryModel);
            return ResponseUtil.successResponse("插入字典值数据成功！");
        } catch (Exception e) {
            log.error("DictionaryController insertDictionaryValue Error: ", e);
            return ResponseUtil.failedResponse("插入字典值数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateDictionaryItem", method = RequestMethod.POST)
    public Object updateDictionaryItem(DictionaryItemModel dictionaryItemModel) {
        try {
            dictionaryService.updateDictionaryItem(dictionaryItemModel);
            return ResponseUtil.successResponse("更新字典项数据成功！");
        } catch (Exception e) {
            log.error("DictionaryController updateDictionaryItem Error: ", e);
            return ResponseUtil.failedResponse("更新字典项数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateDictionaryValue", method = RequestMethod.POST)
    public Object updateDictionaryValue(DictionaryValueModel dictionaryModel) {
        try {
            dictionaryService.updateDictionaryValue(dictionaryModel);
            return ResponseUtil.successResponse("更新字典值数据成功！");
        } catch (Exception e) {
            log.error("DictionaryController updateDictionaryValue Error: ", e);
            return ResponseUtil.failedResponse("更新字典值数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDictionaryItem", method = RequestMethod.GET)
    public Object deleteDictionaryItem(String ids) {
        try {
            dictionaryService.deleteDictionaryItem(ids);
            return ResponseUtil.successResponse("删除字典项数据成功！");
        } catch (Exception e) {
            log.error("DictionaryController deleteDictionaryItem Error: ", e);
            return ResponseUtil.failedResponse("删除字典项数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDictionaryValue", method = RequestMethod.GET)
    public Object deleteDictionaryValue(String ids) {
        try {
            dictionaryService.deleteDictionaryValue(ids);
            return ResponseUtil.successResponse("删除字典值数据成功！");
        } catch (Exception e) {
            log.error("DictionaryController deleteDictionaryValue Error: ", e);
            return ResponseUtil.failedResponse("删除字典值数据失败！", e.getMessage());
        }
    }
}
