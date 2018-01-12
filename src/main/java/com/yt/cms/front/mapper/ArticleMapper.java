package com.yt.cms.front.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.model.Comments;

public interface ArticleMapper {
    
	List<Article> getWebsiteChannelNews(@Param("websiteId") Integer websiteId,@Param("channelId") Integer channelId, @Param("page")  Page page);
	
    long getWebsiteChannelNewsCount(@Param("websiteId") Integer websiteId,@Param("channelId") Integer channelId);
	
    List<Comments> getArticleComments(Integer articleId,Page page);
	
    long getArticleCommentsCount(Integer articleId);
    
    long getArticleCommentsTotal(Integer articleId);
    // 未实现
	long getArticlePV(Integer articleId);
}