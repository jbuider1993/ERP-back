package com.scmp.base.dao;

import com.scmp.base.model.IconModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IIconDao {
    public List<IconModel> getAllIcon(Map<String, Object> queryMap) throws Exception;

    public int getIconsCount(Map<String, Object> queryMap) throws Exception;

    public void addIcon(IconModel iconModel) throws Exception;

    public void updateIcon(IconModel iconModel) throws Exception;
}
