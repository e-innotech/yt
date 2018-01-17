package com.yt.cms.front.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.model.Comments;

public interface ArticleMapper {
    
	/**
	 * 栏目页列表
	 * @param websiteId
	 * @param channelId
	 * @param page
	 * @return
	 */
	List<Article> getWebsiteChannelNews(@Param("websiteId") Integer websiteId,@Param("channelId") Integer channelId, @Param("page")  Page page);
	/**
	 * 栏目页列表
	 * @param websiteId
	 * @param channelId
	 * @return
	 */
    long getWebsiteChannelNewsCount(@Param("websiteId") Integer websiteId,@Param("channelId") Integer channelId);
	
    
	
	Article getWebsiteChannelNewsDetail(@Param("publishId") Integer publishId);
	
    List<Comments> getArticleComments(@Param("publishId") Integer publishId,@Param("page") Page page);
	
    long getArticleCommentsCount(@Param("publishId") Integer publishId);
    
    // 未实现
	long getArticlePV(Integer articleId);
}