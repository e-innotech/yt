package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.News;

/**
 * 稿件接口定义
 * @author admin
 *
 */
public interface NewsService {
	/**
	 * 新增稿件
	 * @param news
	 * @return
	 */
	public boolean save(News news);
	
	/**
	 * 按照id查询稿件
	 * @param id
	 * @return
	 */
	public News findById(Integer id);
	/**
	 * 按照News 参数查询
	 * 列表显示稿件信息
	 * @param news
	 * @return
	 */
	public List<News> queryAll(News news);
	/**
	 * 更新稿件信息
	 * @param news
	 * @return
	 */
	public boolean update(News news);
	/**
	 * 按照稿件id删除稿件
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
}
