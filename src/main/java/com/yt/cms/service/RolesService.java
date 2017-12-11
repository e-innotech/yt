package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.Roles;

/**
 * 角色接口定义
 * @author admin
 *
 */
public interface RolesService {
	/**
	 * 新增角色
	 * @param roles
	 * @return
	 */
	public boolean save(Roles roles);
	
	/**
	 * 按照id查询角色
	 * @param id
	 * @return
	 */
	public Roles findById(Integer id);
	/**
	 * 列表显示角色信息
	 * @return
	 */
	public List<Roles> queryAll();
	/**
	 * 更新角色信息
	 * @param roles
	 * @return
	 */
	public boolean update(Roles roles);
	/**
	 * 按照Roles 参数查询
	 * @param roles
	 * @return
	 */
	public List<Roles> find(Roles roles);
}
