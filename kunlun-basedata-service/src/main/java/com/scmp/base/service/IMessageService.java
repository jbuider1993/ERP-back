package com.scmp.base.service;

import com.scmp.base.model.MessageRecordModel;
import com.scmp.common.model.Page;

import java.util.List;

public interface IMessageService {
    public Page getAllMessages(MessageRecordModel messageModel, int currentPage, int pageSize) throws Exception;

    public void addMessage(MessageRecordModel messageModel) throws Exception;

    public void updateMessage(MessageRecordModel messageModel) throws Exception;

    public void batchDeleteMessage(List<String> ids) throws Exception;
}
