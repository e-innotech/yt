package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.UserGroup;

/**
 * 用户组接口定义
 * @author admin
 *
 */
public interface UserGroupService {
	/**
	 * 新增用户组
	 * @param userGroup
	 * @return
	 */
	public boolean save(UserGroup userGroup);
	/**
	 * 按照id查询用户组
	 * @param id
	 * @return
	 */
	public UserGroup findById(Integer id);
	/**
	 * 批量查询，可以是分页查询
	 * @return
	 */
	public List<UserGroup> query();
	/**
	 * 更新用户组
	 * @param user
	 * @return
	 */
	public boolean update(UserGroup userGroup);

}
