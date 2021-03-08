package com.kunlun.system.service.impl;

import com.kunlun.common.model.Page;
import com.kunlun.system.config.dataSource.DataSourceType;
import com.kunlun.system.config.dataSource.DbContextHolder;
import com.kunlun.system.dao.IMessageDao;
import com.kunlun.system.model.MessageNoticeModel;
import com.kunlun.system.service.IMessageService;
import com.kunlun.system.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("messageService")
@Transactional
public class MessageService implements IMessageService {

    @Autowired
    private IMessageDao messageDao;

    @Override
    public Page getAllMessages(MessageNoticeModel messageModel, int currentPage, int pageSize) throws Exception {
        DbContextHolder.setDbType(DataSourceType.MASTER.getKey());

        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(messageModel, startIndex, pageSize);
        List<MessageNoticeModel> list = messageDao.getAllMessges(queryMap);
        int count = messageDao.getMessageCount(queryMap);

        Page page = new Page(startIndex, pageSize, count, list);
        return page;
    }

    @Override
    public void addMessage(MessageNoticeModel messageModel) throws Exception {
        messageModel.setId(CommonUtil.generateUUID());
        messageModel.setUserId("1111111111111111111");
        messageModel.setCreateTime(new Date());
        messageModel.setModifiedTime(new Date());
        messageDao.addMessage(messageModel);
    }

    @Override
    public void updateMessage(MessageNoticeModel messageModel) throws Exception {
        messageModel.setModifiedTime(new Date());
        messageDao.updateMessage(messageModel);
    }

    @Override
    public void batchDeleteMessage(List<String> ids) throws Exception {
        messageDao.batchDeleteMessage(ids);
    }
}
