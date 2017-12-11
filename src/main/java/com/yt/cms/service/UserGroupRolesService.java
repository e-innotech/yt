package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.Roles;
import com.yt.cms.model.UserGroupRoles;

/**
 * 用户组角色关系接口定义
 * @author admin
 *
 */
public interface UserGroupRolesService {
	/**
	 * 给用户组授予角色权限
	 * @param userGroup
	 * @return
	 */
	public boolean save(UserGroupRoles userGroupRoles);
	/**
	 * 按照用户组id查询用户组角色列表
	 * @param id
	 * @return
	 */
	public List<Roles> findById(Integer id);
	/**
	 * 列表页面查询，显示用户组信息与角色信息
	 * @return
	 */
	public List<UserGroupRoles> queryAll();
	/**
	 * 更新用户组角色
	 * @param user
	 * @return
	 */
	public boolean update(UserGroupRoles userGroupRoles);

}
