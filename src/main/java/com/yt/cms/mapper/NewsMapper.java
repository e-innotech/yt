package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
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

    List<News> query(@Param("news") News news, @Param("page") Page page);
    
    long queryCount(News news);
    
    int deleteLogicById(Integer id);
    /**
     * 更新status
     * @param record
     * @return
     */
    int updateStatusByPrimaryKey(News record);
}