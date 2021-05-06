package com.kunlun.basedata.dao;

import com.kunlun.basedata.model.WorkPostModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 岗位数据库Dao
 */
@Repository
public interface IWorkPostDao {

    public List<WorkPostModel> getAllWorkPost(Map<String, Object> queryMap) throws Exception;

    public int getWorkPostCount(Map<String, Object> queryMap) throws Exception;

    public void addWorkPost(WorkPostModel workPostModel) throws Exception;

    public void updateWorkPost(WorkPostModel workPostModel) throws Exception;

    public void deleteWorkPost(List<String> list) throws Exception;
}
