package com.scmp.base.service;

import com.scmp.base.model.IconModel;
import com.scmp.common.model.Page;

public interface IIconService {
    public Page getAllIcon(IconModel iconModel, int currentPage, int pageSize) throws Exception;

    public void addIcon(IconModel iconModel) throws Exception;

    public void updateIcon(IconModel iconModel) throws Exception;
}
