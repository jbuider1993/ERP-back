package com.scmp.common.utils;

import com.scmp.common.model.Page;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class ListPageUtil {

    public static <T> Page limitPages(List<T> list, int currentPage, int pageSize) {
        Page page = new Page();
        if (ObjectUtils.isEmpty(list)) {
            return page;
        }

        int startIndex = currentPage * pageSize;
        int mod = list.size() % pageSize;
        List<T> result = null;
        if (mod == 0) {
            result = list.subList(startIndex, pageSize);
        } else {
            result = list.subList(startIndex, mod);
        }

        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(list.size());
        page.setRecords(result);
        return page;
    }
}
