package com.kunlun.basedata.dao;

import com.kunlun.basedata.model.UserModel;
import com.kunlun.common.model.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IUserDao {
    public List<Page> getAllUser(Map<String, Object> queryMap) throws Exception;

    public int getUsersCount(Map<String, Object> queryMap) throws Exception;

    public void addUser(UserModel user) throws Exception;

    public void updateUser(UserModel user) throws Exception;

    public void batchDeleteUser(List<String> ids) throws Exception;

    public UserModel getUserByUserName(@Param("userName") String userName) throws Exception;
}
