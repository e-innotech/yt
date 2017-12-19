package com.yt.cms.service;

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
	 * 更新用户组角色
	 * @param user
	 * @return
	 */
	public boolean update(UserGroupRoles userGroupRoles);

}
