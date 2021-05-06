package com.kunlun.basedata.controller;

import com.kunlun.basedata.model.WorkPostModel;
import com.kunlun.basedata.service.IWorkPostService;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 岗位Controller类
 */
@RestController
@RequestMapping(value = "/workPost")
public class WorkPostController {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private IWorkPostService workPostService;

    @RequestMapping(value = "/getAllWorkPost", method = RequestMethod.GET)
    public Object getAllWorkPost(WorkPostModel workPostModel, int currentPage, int pageSize) {
        try {
            Page<WorkPostModel> workPosts = workPostService.getAllWorkPost(workPostModel, currentPage, pageSize);
            return ResponseUtil.successResponse(workPosts);
        } catch (Exception e) {
            logger.error("WorkPostController getAllWorkPost Error: ", e);
            return ResponseUtil.failedResponse("获取岗位数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addWorkPost", method = RequestMethod.POST)
    public Object addWorkPost(WorkPostModel workPostModel) {
        try {
            workPostService.addWorkPost(workPostModel);
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            logger.error("WorkPostController addWorkPost Error: ", e);
            return ResponseUtil.failedResponse("添加岗位失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateWorkPost", method = RequestMethod.POST)
    public Object updateWorkPost(WorkPostModel workPostModel) {
        try {
            workPostService.updateWorkPost(workPostModel);
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            logger.error("WorkPostController updateWorkPost Error: ", e);
            return ResponseUtil.failedResponse("更新岗位失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteWorkPost", method = RequestMethod.POST)
    public Object deleteWorkPost(List<String> list) {
        try {
            workPostService.deleteWorkPost(list);
            return ResponseUtil.successResponse("SUCCESS");
        } catch (Exception e) {
            logger.error("WorkPostController deleteWorkPost Error: ", e);
            return ResponseUtil.failedResponse("删除岗位失败！", e.getMessage());
        }
    }
}
