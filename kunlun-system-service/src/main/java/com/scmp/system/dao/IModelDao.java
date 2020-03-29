package com.scmp.system.dao;

import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IModelDao {
    public List<Model> getAllProcess(Map<String, Object> queryMap) throws Exception;

    public int getProcessesCount(Map<String, Object> queryMap) throws Exception;

    public boolean batchDeleteModel(String[] processIds) throws Exception;
}
