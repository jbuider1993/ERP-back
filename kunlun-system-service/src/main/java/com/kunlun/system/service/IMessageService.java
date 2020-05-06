package com.kunlun.system.service;

import com.kunlun.common.model.Page;
import com.kunlun.system.model.MessageNoticeModel;

import java.util.List;

public interface IMessageService {
    public Page getAllMessages(MessageNoticeModel messageModel, int currentPage, int pageSize) throws Exception;

    public void addMessage(MessageNoticeModel messageModel) throws Exception;

    public void updateMessage(MessageNoticeModel messageModel) throws Exception;

    public void batchDeleteMessage(List<String> ids) throws Exception;
}
