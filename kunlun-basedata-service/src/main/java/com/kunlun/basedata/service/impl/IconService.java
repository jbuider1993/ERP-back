package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.IconModel;
import com.kunlun.basedata.service.IIconService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.basedata.dao.IIconDao;
import com.kunlun.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class IconService implements IIconService {

    @Autowired
    private IIconDao iconDao;

    @Override
    public Page getAllIcon(IconModel iconModel, int currentPage, int pageSize) throws Exception {
        int startIndex = (currentPage - 1) * pageSize;
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(iconModel, startIndex, pageSize);

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(iconDao.getIconsCount(queryMap));
        page.setRecords(iconDao.getAllIcon(queryMap));
        return page;
    }

    @Override
    public void addIcon(IconModel iconModel) throws Exception {
        iconDao.addIcon(iconModel);
    }

    @Override
    public void updateIcon(IconModel iconModel) throws Exception {
        iconDao.updateIcon(iconModel);
    }
}
