package com.kunlun.system.dao;

import com.kunlun.system.model.ProcessModel;
import com.kunlun.system.model.activiti.ActInstModel;
import com.kunlun.system.model.activiti.ProcDefModel;
import com.kunlun.system.model.activiti.ProcInstModel;
import com.kunlun.system.model.activiti.TaskInstModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IProcessDao {
    public List<ProcessModel> getAllProcess(Map<String, Object> queryMap) throws Exception;

    public int getProcessCount(Map<String, Object> queryMap) throws Exception;

    public List<ProcessModel> getAllTodo(Map<String, Object> queryMap) throws Exception;

    public int getTodoCount(Map<String, Object> queryMap) throws Exception;

    public List<ProcDefModel> getProcDefs(Map<String, Object> queryMap) throws Exception;

    public List<ProcInstModel> getProcInsts(Map<String, Object> queryMap) throws Exception;

    public List<ActInstModel> getActInsts(Map<String, Object> queryMap) throws Exception;

    public List<TaskInstModel> getTaskInsts(Map<String, Object> queryMap) throws Exception;
}
