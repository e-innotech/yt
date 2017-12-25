package com.yt.cms.service;

import java.util.List;

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
	public boolean save(List<UserGroupRoles> userGroupRoles);

	/**
	 * 批量更新
	 * 更新一个用户组对应的角色数据
	 * 用户组和角色关系的更新，可以理解成旧关系的解除与新关系的建立
	 * 前端传递之前的用户组配置的角色id值，更新之后的角色id值
	 * 如 用户组1 关联的角色是 A、B、C 修改成 A、B、D、E 则后台insert D|E， delete C
	 * 前端传递2个id数组列表
	 * @param userGroupId
	 * @param old_rolesIds
	 * @param new_rolesIds
	 * @return
	 */
	public boolean update(Integer userGroupId, Integer[] old_rolesIds, Integer[] new_rolesIds);

}
