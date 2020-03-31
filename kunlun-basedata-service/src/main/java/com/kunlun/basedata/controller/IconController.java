package com.kunlun.basedata.controller;

import com.kunlun.basedata.model.IconModel;
import com.kunlun.basedata.service.IIconService;
import com.kunlun.basedata.service.ISeleniumService;
import com.scmp.common.model.Page;
import com.scmp.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/icon")
public class IconController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private IIconService iconService;

    @Autowired
    private ISeleniumService seleniumService;

    @RequestMapping(value = "/getAllIcon", method = RequestMethod.GET)
    public Object getAllIcon(IconModel iconModel, int currentPage, int pageSize) {
        try {
            Page icons = iconService.getAllIcon(iconModel, currentPage, pageSize);
            return ResponseUtil.successResponse(icons);
        } catch (Exception e) {
            log.error("IconController getAllIcon Error: ", e);
            return ResponseUtil.failedResponse("获取系统平台图标！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addIcon", method = RequestMethod.POST)
    public Object addIcon(IconModel iconModel) {
        try {
            iconService.addIcon(iconModel);
            return ResponseUtil.successResponse(iconModel);
        } catch (Exception e) {
            log.error("IconController addIcon Error: ", e);
            return ResponseUtil.failedResponse("获取系统平台图标！", e.getMessage());
        }
    }

    @RequestMapping(value = "/getIconinfo", method = RequestMethod.GET)
    public Object getIconinfo(IconModel iconModel) {
        try {
            iconService.updateIcon(iconModel);
            return ResponseUtil.successResponse(iconModel);
        } catch (Exception e) {
            log.error("IconController getAllMenu Error: ", e);
            return ResponseUtil.failedResponse("获取系统平台图标！", e.getMessage());
        }
    }

    @RequestMapping(value = "/fetchIconsFromAntd", method = RequestMethod.GET)
    public Object fetchIconsFromAntd() {
        try {
            seleniumService.executeStart();
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("IconController fetchIconsFromAntd Error: ", e);
            return ResponseUtil.failedResponse("获取图标失败！", e.getMessage());
        }
    }
}
