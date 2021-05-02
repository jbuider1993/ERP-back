package com.kunlun.common.utils;

import com.kunlun.common.model.TreeModel;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TreeUtil {

    public static <T extends TreeModel> void packageListToTree(List<T> rootNodes, List<T> list) {
        for (T model : rootNodes) {
            List<T> children = list.stream().filter(obj -> model.getId().equals(obj.getParentId())).collect(Collectors.toList());
            if (!ObjectUtils.isEmpty(children)) {
                model.setChildren(children);
                packageListToTree(children, list);
            } else {
                model.setLeaf(true);
            }
        }
    }
}
