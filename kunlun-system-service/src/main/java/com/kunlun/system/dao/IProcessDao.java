package com.kunlun.system.dao;

import com.kunlun.system.model.ProcessModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IProcessDao {
    public List<ProcessModel> getAllProcess(Map<String, Object> queryMap) throws Exception;

    public int getProcessCount(Map<String, Object> queryMap) throws Exception;

    public List<ProcessModel> getAllTodo(Map<String, Object> queryMap) throws Exception;

    public int getTodoCount(Map<String, Object> queryMap) throws Exception;
}
