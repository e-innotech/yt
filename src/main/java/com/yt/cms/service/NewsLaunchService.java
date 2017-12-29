package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.model.NewsLaunch;

/**
 * 稿件投放接口定义
 * 稿件投放,编辑稿件投放，稿件审核人审批稿件投放情况
 * 投稿人选择网站，栏目，提交到news_launch 表
 * 投稿网站可以选择多个，一个投稿网站可以选择多个栏目
 * 网站与栏目关联关系用json写到news_launch_config 字段
 * @author admin
 *
 */
public interface NewsLaunchService {
	/**
	 * 新增稿件投放
	 * @param newsLaunch
	 * @return
	 */
	public boolean save(NewsLaunch newsLaunch);
	
	/**
	 * 按照投放id查询稿件投放情况
	 * @param id
	 * @return
	 */
	public NewsLaunch findById(Integer id);
	
	/**
	 * 更新稿件投放信息
	 * @param newsLaunch
	 * @return
	 */
	public boolean update(NewsLaunch newsLaunch);
	/**
	 * 按照投放id删除
	 * @param id
	 * @return
	 */
	public boolean deleteLogicById(Integer id);
	
	/**
	 * 审批稿件
	 * @param newsLaunch
	 * @return
	 */
	public boolean aduit(NewsLaunch newsLaunch);
	/**
	 * 分页查询投放列表
	 * @param newsLaunch
	 * @param page
	 * @return
	 */
	public List<NewsLaunch> queryAll(NewsLaunch newsLaunch, Page page);
	
}
