package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.NewsLaunch;

public interface NewsLaunchMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(NewsLaunch record);

    NewsLaunch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsLaunch record);
    
    int deleteLogicById(Integer id);
    
    List<NewsLaunch> query(@Param("record") NewsLaunch record, @Param("page") Page page);
    
    long queryCount(NewsLaunch record);

}