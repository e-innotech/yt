package com.yt.cms.mapper;

import com.yt.cms.model.NewsLaunch;

public interface NewsLaunchMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(NewsLaunch record);

    NewsLaunch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsLaunch record);

}