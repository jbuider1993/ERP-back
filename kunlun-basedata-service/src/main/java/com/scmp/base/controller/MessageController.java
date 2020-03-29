package com.scmp.base.controller;

import com.scmp.base.model.MessageRecordModel;
import com.scmp.base.service.IMessageService;
import com.scmp.common.model.Page;
import com.scmp.common.utils.ResponseUtil;
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
    public Object getAllMessages(MessageRecordModel messageModel, int currentPage, int pageSize) {
        try {
            Page users = messageService.getAllMessages(messageModel, currentPage, pageSize);
            return ResponseUtil.successResponse(users);
        } catch (Exception e) {
            log.error("MessageController getAllMessages Error: ", e);
            return ResponseUtil.failedResponse("查询所有消息失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public Object addMessage(MessageRecordModel messageModel) {
        try {
            messageService.addMessage(messageModel);
            return ResponseUtil.successResponse(messageModel);
        } catch (Exception e) {
            log.error("MessageController addMessage Error: ", e);
            return ResponseUtil.failedResponse("新增消息失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
    public Object updateMessage(MessageRecordModel messageModel) {
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
