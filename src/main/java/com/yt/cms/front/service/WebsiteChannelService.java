package com.yt.cms.front.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Article;

/**
 * 网站栏目页请求接口定义
 * @author admin
 *
 */
public interface WebsiteChannelService {
	
	
	// 详情页页面接口      
	// 首页页面接口（后台置首页，取消置首页触发，下线如果在首页也需要触发）
	// 提供给前端使用的首页页面接口（手动触发上传到7牛）
	
	// 前端请求接口返回数据填充页面，请求网站URL读文件写7牛，并将静态文件也上传7牛
	
	
	/**
	 * 获取网站栏目文章列表
	 * @param websiteId
	 * @param channelId
	 * @return [{id，标题，出处，发布时间，部分内容，链接地址，点赞数量，收藏数量，评论数}]
	 */
	public List<Article> getWebsiteChannelNews(Integer websiteId,Integer channelId,Page page);
	/**
	 * 获取网站栏目文章列表 -分页count
	 * @param websiteId
	 * @param channelId
	 * @return
	 */
	public long getWebsiteChannelNewsCount(Integer websiteId, Integer channelId);
	
}
