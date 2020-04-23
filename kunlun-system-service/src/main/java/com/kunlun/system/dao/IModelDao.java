package com.kunlun.system.dao;

import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IModelDao {
    public List<Model> getAllModels(Map<String, Object> queryMap) throws Exception;

    public int getModelsCount(Map<String, Object> queryMap) throws Exception;

    public List<Map<String, String>> getModelBytearrays(String[] processIds) throws Exception;

    public boolean batchDeleteModel(String[] processIds) throws Exception;

    public boolean batchDeleteModelBytearray(List<String> valueIds) throws Exception;
}
