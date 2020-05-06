package com.kunlun.system.controller;

import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ResponseUtil;
import com.kunlun.system.model.MessageNoticeModel;
import com.kunlun.system.service.IMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    private Logger log = LogManager.getLogger();

    @Autowired
    @Qualifier("messageService")
    private IMessageService messageService;

    @RequestMapping(value = "/getAllMessages", method = RequestMethod.GET)
    public Object getAllMessages(MessageNoticeModel messageModel, int currentPage, int pageSize) {
        try {
            Page users = messageService.getAllMessages(messageModel, currentPage, pageSize);
            return ResponseUtil.successResponse(users);
        } catch (Exception e) {
            log.error("MessageController getAllMessages Error: ", e);
            return ResponseUtil.failedResponse("查询所有消息失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public Object addMessage(MessageNoticeModel messageModel) {
        try {
            messageService.addMessage(messageModel);
            return ResponseUtil.successResponse(messageModel);
        } catch (Exception e) {
            log.error("MessageController addMessage Error: ", e);
            return ResponseUtil.failedResponse("新增消息失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
    public Object updateMessage(MessageNoticeModel messageModel) {
        try {
            messageService.updateMessage(messageModel);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("MessageController updateMessage Error: ", e);
            return ResponseUtil.failedResponse("修改消息失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/batchDeleteMessage", method = RequestMethod.POST)
    public Object batchDeleteMessage(String ids) {
        try {
            List<String> idList = Arrays.asList(ids.split(","));
            messageService.batchDeleteMessage(idList);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("MessageController batchDeleteMessage Error: ", e);
            return ResponseUtil.failedResponse("删除消息失败！", e.getMessage());
        }
    }
}
