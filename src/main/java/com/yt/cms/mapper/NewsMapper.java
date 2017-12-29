package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.News;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);
    /**
     * 稿件修改，稿件审批使用
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(News record);

    List<News> query(News news);
    
    int deleteLogicById(Integer id);
}