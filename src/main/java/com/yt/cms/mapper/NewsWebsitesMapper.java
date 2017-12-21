package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.NewsWebsites;

public interface NewsWebsitesMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(NewsWebsites record);

    NewsWebsites selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsWebsites record);
    /**
     * 稿件投放网站
     * @param newsId
     * @param websitesIds
     */
    void insertBatch(@Param("newsId") Integer newsId, @Param("websitesIds") List<Integer> websitesIds);
    
    
}