package com.yt.cms.front.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Article;

public interface WebsiteHomeMapper {
	
    long getWebsiteHomeByWeightCount(@Param("websiteId") Integer websiteId,@Param("homeWeight") Integer homeWeight);
	
    List<Article> getWebsiteHomeByWeight(@Param("websiteId") Integer websiteId,@Param("homeWeight") Integer homeWeight, @Param("page")  Page page);
	
}