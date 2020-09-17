package com.kunlun.basedata.service.impl;

import com.kunlun.basedata.model.IconModel;
import com.kunlun.basedata.service.IIconService;
import com.kunlun.basedata.utils.CommonUtil;
import com.kunlun.basedata.dao.IIconDao;
import com.kunlun.common.model.Page;
import com.kunlun.common.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public void insertBatch(List<IconModel> iconModels) throws Exception {
        iconDao.insertBatch(iconModels);
    }

    @Override
    public void deleteAllIcon() throws Exception {
        iconDao.deleteAllIcon();
    }

    @Override
    public void onExportIcons(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> queryMap = CommonUtil.packageQueryMap(new IconModel(), 0, 99999);
        List<IconModel> iconModels = iconDao.getAllIcon(queryMap);
        String[] headerNames = new String[]{"图标名称", "图标key", "抓取时间", "更新时间"};
        String[] fieldNames = new String[]{"name", "key", "createTime", "modifiedTime"};
        int[] lineWidths = new int[]{80, 80, 80, 80};

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dataSource", iconModels);
        paramMap.put("sheetName", "操作日志");
        paramMap.put("headerNames", headerNames);
        paramMap.put("fieldNames", fieldNames);
        paramMap.put("lineWidths", lineWidths);
        ExcelUtil.exportExcel(request, response, IconModel.class, paramMap);
    }
}
