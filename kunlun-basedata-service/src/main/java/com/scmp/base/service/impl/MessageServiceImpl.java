package com.scmp.base.service.impl;

import com.scmp.base.dao.IMessageDao;
import com.scmp.base.model.MessageRecordModel;
import com.scmp.base.service.IMessageService;
import com.scmp.base.utils.CommonUtil;
import com.scmp.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private IMessageDao messageDao;

    @Override
    public Page getAllMessages(MessageRecordModel messageModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(messageModel, startIndex, pageSize);
        List<MessageRecordModel> list = messageDao.getAllMessges(queryMap);
        int count = messageDao.getMessageCount(queryMap);

        Page page = new Page(startIndex, pageSize, count, list);
        return page;
    }

    @Override
    public void addMessage(MessageRecordModel messageModel) throws Exception {
        messageModel.setId(CommonUtil.generateUUID());
        messageModel.setUserId("1111111111111111111");
        messageModel.setCreateTime(new Date());
        messageModel.setModifiedTime(new Date());
        messageDao.addMessage(messageModel);
    }

    @Override
    public void updateMessage(MessageRecordModel messageModel) throws Exception {
        messageModel.setModifiedTime(new Date());
        messageDao.updateMessage(messageModel);
    }

    @Override
    public void batchDeleteMessage(List<String> ids) throws Exception {
        messageDao.batchDeleteMessage(ids);
    }
}
