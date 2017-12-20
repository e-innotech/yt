package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.NavigationBar;

public interface NavigationBarMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(NavigationBar record);

    NavigationBar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavigationBar record);
    
    List<NavigationBar> query(NavigationBar record);
}