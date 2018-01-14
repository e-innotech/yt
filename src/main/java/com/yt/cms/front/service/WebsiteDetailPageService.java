package com.yt.cms.front.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.model.Comments;

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
 * 
 * 
 * 
 * 
 * @author admin
 *
 */
public interface WebsiteDetailPageService {
	/**
	 * 获取网站栏目文章列表
	 * @param websiteId
	 * @param channelId
	 * @return [{id，标题，出处，发布时间，部分内容，链接地址，点赞数量，收藏数量，评论数}]
	 */
	public Article getWebsiteChannelNewsDetail(Integer websiteId,Integer channelId,Integer articleId);
	
	/**
	 * 获取文章评论数据
	 * 后期将文章评论转移到redis
	 * @param websiteId
	 * @param articleId
	 * @return {评论人图像，评论人会员名、注册时间、评论内容、评论时间}、评论加载更多（在上一页评论数据中追加数据）
	 */
	public List<Comments> getArticleComments(Integer websiteId,Integer articleId,Page page);

	/**
	 * 获取文章评论数量
	 * 后期将文章评论数转移到redis
	 * @param websiteId
	 * @param articleId
	 * @return
	 */
	public long getArticleCommentNum(Integer websiteId,Integer articleId);
	
	/**
	 * 获取文章访问量pv
	 * 后期将文章评论数转移到redis
	 * @param articleId
	 * @return
	 */
	public long getArticleCommentsTotal(Integer websiteId,Integer articleId);
	
	/**
	 * 获取文章访问量pv
	 * 后期将文章评论数转移到redis
	 * @param articleId
	 * @return
	 */
	public long getArticlePV(Integer websiteId,Integer articleId);
}
