package com.yt.cms.service;

import java.util.List;

/**
 * 对象CRUD接口定义
 * @author admin
 *
 */
public interface BaseService<T> {
	/**
	 * 新增对象
	 * @param model
	 * @return
	 */
	public boolean save(T model);
	
	/**
	 * 按照id查询对象
	 * @param id
	 * @return
	 */
	public T findById(Integer id);
	/**
	 * 列表显示对象信息
	 * @return
	 */
	public List<T> queryAll(T model);
	/**
	 * 更新对象信息
	 * @param model
	 * @return
	 */
	public boolean update(T model);
	/**
	 * 按照对象 参数查询
	 * @param model
	 * @return
	 */
	public List<T> find(T model);
}
