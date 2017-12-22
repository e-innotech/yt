package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.NewsPublish;
import com.yt.cms.model.NewsPublishLine;

/**
 * 稿件上下线接口定义
 * 稿件上下线管理列表，稿件上线与下线,网站首页稿件权重设置，
 * 稿件审批人审核通过的稿件投放信息提交到news_publish 表
 * 投稿网站可以选择多个，一个投稿网站可以选择多个栏目，如果选择5个网站，每个网站选择4个栏目，
 * 则在news_publish 表中生成 5*4条记录
 * 可对单独的记录做上下线管理
 * @author admin
 *
 */
public interface NewsPublishService {
	/**
	 * 批量稿件发布（上线）
	 * @param newsPublishs
	 * @return
	 */
	public boolean save(List<NewsPublish> newsLaunchs);
	
	/**
	 * 按照稿件id查看投放情况
	 * @param id
	 * @return
	 */
	public NewsPublishLine findById(Integer id);
	
	/**
	 * 稿件下线以及网站首页稿件权重设置
	 * @param newsPublish
	 * @return
	 */
	public boolean update(NewsPublish newsPublish);

	/**
	 * 稿件上下线管理列表
	 * @param newsPublish
	 * @return
	 */
	public List<NewsPublish> query(NewsPublish newsPublish);
	
	
	
}
