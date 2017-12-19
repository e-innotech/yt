package com.yt.cms.service;

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
	 * 更新角色资源关系信息
	 * @param rolesResource
	 * @return
	 */
	public boolean update(RolesResource rolesResource);
	
}
