package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
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
	 * 分页查询
	 * @return
	 */
	
	public List<UserGroup> query(String groupName, Page page);
	/**
	 * 查询count
	 * @param groupName
	 * @return
	 */
	public long queryCount(String groupName);
	/**
	 * 查询所有，树形展现
	 * @return
	 */
	public List<UserGroup> queryAll();
	/**
	 * 更新用户组
	 * @param user
	 * @return
	 */
	public boolean update(UserGroup userGroup);
	/**
	 * 按照id删除用户组
	 * @param id
	 * @return
	 */
	public boolean deleteLogicById(Integer id);

}
