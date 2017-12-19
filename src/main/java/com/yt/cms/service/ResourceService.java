package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.Resource;

/**
 * 资源接口定义
 * @author admin
 *
 */
public interface ResourceService {
	/**
	 * 新增资源
	 * @param resource
	 * @return
	 */
	public boolean save(Resource resource);
	
	/**
	 * 按照id查询资源
	 * @param id
	 * @return
	 */
	public Resource findById(Integer id);

	/**
	 * 更新资源信息
	 * @param resource
	 * @return
	 */
	public boolean update(Resource resource);
	/**
	 * 按照resource 参数查询
	 * @param resource
	 * @return
	 */
	public List<Resource> find(Resource resource);
	/**
	 * 按照资源id删除资源
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	/**
	 * 设置资源所属模块
	 * @param id
	 * @param moduleId
	 * @return
	 */
	public boolean setModule(Integer id, Integer moduleId);
}
