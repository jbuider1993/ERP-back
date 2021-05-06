package com.kunlun.basedata.service;

import com.kunlun.basedata.model.WorkPostModel;
import com.kunlun.common.model.Page;

import java.util.List;

/**
 * 岗位业务接口
 */
public interface IWorkPostService {

    public Page getAllWorkPost(WorkPostModel workPostModel, int currentPage, int pageSize) throws Exception;

    public void addWorkPost(WorkPostModel workPostModel) throws Exception;

    public void updateWorkPost(WorkPostModel workPostModel) throws Exception;

    public void deleteWorkPost(List<String> list) throws Exception;
}
