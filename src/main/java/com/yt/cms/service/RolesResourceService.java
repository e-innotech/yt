package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.RolesResource;

/**
 * 角色资源关系接口定义
 * @author admin
 *
 */
public interface RolesResourceService {
	/**
	 * 新增角色资源关系
	 * @param rolesResource
	 * @return
	 */
	public boolean save(RolesResource rolesResource);
	
	/**
	 * 按照id查询角色资源关系
	 * @param id
	 * @return
	 */
	public RolesResource findById(Integer id);

	/**
	 * 更新角色资源关系信息
	 * @param rolesResource
	 * @return
	 */
	public boolean update(RolesResource rolesResource);
	/**
	 * 按照RolesResource 参数查询
	 * @param rolesResource
	 * @return
	 */
	public List<RolesResource> find(RolesResource rolesResource);
}
