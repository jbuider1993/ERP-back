package com.kunlun.system.dao;

import com.kunlun.system.model.MessageNoticeModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IMessageDao {
    public List<MessageNoticeModel> getAllMessges(Map<String, Object> queryMap) throws Exception;

    public int getMessageCount(Map<String, Object> queryMap) throws Exception;

    public void addMessage(MessageNoticeModel message) throws Exception;

    public void updateMessage(MessageNoticeModel message) throws Exception;

    public void batchDeleteMessage(List<String> ids) throws Exception;
}
