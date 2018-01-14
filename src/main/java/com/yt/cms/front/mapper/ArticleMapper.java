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
	
    
    
	
	Article getWebsiteChannelNewsDetail(@Param("websiteId") Integer websiteId,@Param("channelId") Integer channelId, @Param("articleId")  Integer articleId);
	
    List<Comments> getArticleComments(@Param("websiteId") Integer websiteId,@Param("articleId") Integer articleId,@Param("page") Page page);
	
    long getArticleCommentsCount(@Param("websiteId") Integer websiteId,@Param("articleId") Integer articleId);
    
    long getArticleCommentsTotal(@Param("websiteId") Integer websiteId,@Param("articleId") Integer articleId);
    // 未实现
	long getArticlePV(Integer articleId);
}