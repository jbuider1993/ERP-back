package com.kunlun.basedata.service;

import com.kunlun.basedata.model.IconModel;
import com.kunlun.common.model.Page;

import java.util.List;

public interface IIconService {
    public Page getAllIcon(IconModel iconModel, int currentPage, int pageSize) throws Exception;

    public void addIcon(IconModel iconModel) throws Exception;

    public void updateIcon(IconModel iconModel) throws Exception;

    public void insertBatch(List<IconModel> iconModels) throws Exception;

    public void deleteAllIcon() throws Exception;
}
