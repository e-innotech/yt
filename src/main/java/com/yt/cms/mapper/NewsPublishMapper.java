package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.model.NewsPublishLine;

public interface NewsPublishMapper {
    
	void insertBatch(List<NewsPublish> records);
	
    int insertSelective(NewsPublish record);
    /**
     * 关联查询出稿件投放的网站，栏目名称
     * @param id
     * @return
     */
    NewsPublishLine selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(NewsPublish record);

    int deleteByPrimaryKey(Integer id);
    
    List<NewsPublish> query(@Param("record") NewsPublish record, @Param("page") Page page);
    
    long queryCount(NewsPublish record);
    
}