package com.yt.cms.front.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Article;

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
 * 
 * @author admin
 *
 */
public interface WebsiteHomeService {

	
	/**
	 * 首页权重栏位数据
	 * @param websiteId
	 * @param homeWeight
	 * @param page
	 * @return
	 */
	public List<Article> getWebsiteHomeByWeight(Integer websiteId,Integer homeWeight,Page page); 
	/**
	 * 首页需要分页的权重栏位
	 * @param websiteId
	 * @param homeWeight
	 * @return
	 */
	public long getWebsiteHomeByWeightCount(Integer websiteId,Integer homeWeight);
}
