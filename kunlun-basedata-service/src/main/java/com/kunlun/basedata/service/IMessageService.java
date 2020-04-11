package com.kunlun.basedata.service;

import com.kunlun.basedata.model.MessageRecordModel;
import com.kunlun.common.model.Page;

import java.util.List;

public interface IMessageService {
    public Page getAllMessages(MessageRecordModel messageModel, int currentPage, int pageSize) throws Exception;

    public void addMessage(MessageRecordModel messageModel) throws Exception;

    public void updateMessage(MessageRecordModel messageModel) throws Exception;

    public void batchDeleteMessage(List<String> ids) throws Exception;
}
