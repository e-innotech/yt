package com.yt.cms.front.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Ad;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.model.Comments;
import com.yt.cms.model.Channel;

/**
 * 网站栏目页请求接口定义
 * @author admin
 *
 */
public interface WebsiteChannelService {
	
	
	
	
	
	/**
	 * 获取网站的栏目列表
	 * @param websiteId
	 * @return {栏目名称，栏目id，网站id}
	 */
	public List<Channel> getWebsiteChannel(Integer websiteId);
	/**
	 * 获取网站栏目文章列表
	 * @param websiteId
	 * @param channelId
	 * @return [{id，标题，出处，发布时间，部分内容，链接地址，点赞数量，收藏数量，评论数}]
	 */
	public List<Article> getWebsiteChannelNews(Integer websiteId,Integer channelId,Page page);
	/**
	 * 获取网站模板广告列表
	 * @param websiteId
	 * @param templateType
	 * @return
	 */
	public List<Ad> getWebsiteTemplateAds(Integer websiteId, Integer templateType);
	/**
	 * 获取文章评论数据
	 * 后期将文章评论转移到redis
	 * @param articleId
	 * @return {评论人图像，评论人会员名、注册时间、评论内容、评论时间}、评论加载更多（在上一页评论数据中追加数据）
	 */
	public List<Comments> getArticleComments(Integer articleId,Page page);
	/**
	 * 获取文章评论数量
	 * 后期将文章评论数转移到redis
	 * @param articleId
	 * @return
	 */
	public long getArticleCommentNum(Integer articleId);
	
	/**
	 * 获取文章访问量pv
	 * 后期将文章评论数转移到redis
	 * @param articleId
	 * @return
	 */
	public long getArticlePV(Integer articleId);
	
}
