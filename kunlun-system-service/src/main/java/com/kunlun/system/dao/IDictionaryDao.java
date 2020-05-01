package com.kunlun.system.dao;

import com.kunlun.common.model.OperatorLogModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IDictionaryDao {
    public List<OperatorLogModel> getAllOperateLog(Map<String, Object> queryMap) throws Exception;

    public int getOperateLogCount(Map<String, Object> queryMap) throws Exception;

    public void insertOperateLog(OperatorLogModel logModel) throws Exception;

    public void updateOperateLog(OperatorLogModel logModel) throws Exception;
}
