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
	public boolean save(List<RolesResource> rolesResource);

	/**
	 * 更新角色资源关系信息
	 * @param rolesId
	 * @param old_resourceIds
	 * @param new_resourceIds
	 * @return
	 */
	public boolean update(Integer rolesId, Integer[] old_resourceIds, Integer[] new_resourceIds);
	
}
