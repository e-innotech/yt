package com.yt.cms.front.service;

import java.util.List;

import com.yt.cms.front.model.Ad;
import com.yt.cms.front.model.Channel;

/**
 * 
 * 1、网站栏目接口   ok
2、广告接口  ok
3、首页权重查询数据接口  生成页面上传7牛 需要删除文件
4、栏目页面的文章列表接口 ok
5、详情页文章数据接口  生成页面上传7牛  不能删除文件
6、文章评论数  
7、文章点击数
8、文章评论数据接口  ok
 * @author admin
 *
 */
public interface WebsiteCommonService {

	/**
	 * 获取网站的栏目列表
	 * @param websiteId
	 * @return {栏目名称，栏目id，网站id}
	 */
	public List<Channel> getWebsiteChannel(Integer websiteId);
	
	/**
	 * 获取网站模板广告列表
	 * @param websiteId
	 * @param templateType
	 * @return
	 */
	public List<Ad> getWebsiteTemplateAds(Integer websiteId, Integer templateType);
}
