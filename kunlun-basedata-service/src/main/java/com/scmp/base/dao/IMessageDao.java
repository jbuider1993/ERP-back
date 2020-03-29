package com.scmp.base.dao;

import com.scmp.base.model.MessageRecordModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IMessageDao {
    public List<MessageRecordModel> getAllMessges(Map<String, Object> queryMap) throws Exception;

    public int getMessageCount(Map<String, Object> queryMap) throws Exception;

    public void addMessage(MessageRecordModel message) throws Exception;

    public void updateMessage(MessageRecordModel message) throws Exception;

    public void batchDeleteMessage(List<String> ids) throws Exception;
}
