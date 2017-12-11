package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.RolesResource;

/**
 * 角色资源接口定义
 * @author admin
 *
 */
public interface RolesResourceService {
	/**
	 * 新增角色
	 * @param rolesResource
	 * @return
	 */
	public boolean save(RolesResource rolesResource);
	
	/**
	 * 按照id查询角色
	 * @param id
	 * @return
	 */
	public RolesResource findById(Integer id);
	/**
	 * 列表显示角色信息
	 * @return
	 */
	public List<RolesResource> queryAll();
	/**
	 * 更新角色信息
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
