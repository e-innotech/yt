package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.User;

/**
 * 用户接口定义
 * @author admin
 *
 */
public interface UserService {
	/**
	 * 写入一个用户
	 * @param user
	 * @return
	 */
	public boolean saveOne(User user);
	/**
	 * 按照id查询用户
	 * @param id
	 * @return
	 */
	public User findById(Integer id);
	/**
	 * 批量查询，可以是分页查询
	 * @return
	 */
	public List<User> queryAll();
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public boolean update(User user);
	/**
	 * 对用户的启用或者禁用
	 * @param user
	 * @return
	 */
	public boolean disableOrEnable(User user);
}
