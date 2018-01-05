package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.model.User;
import com.yt.cms.model.UserInfoUpdate;
import com.yt.cms.model.UserUpdatePwd;

/**
 * 用户接口定义
 * @author admin
 *
 */
public interface UserService {
	/**
	 * 新增用户（注册或者管理员新增）
	 * @param user
	 * @return
	 */
	public boolean save(User user);
	/**
	 * 用户登录
	 * 输入的密码是加密处理的
	 * @param user
	 * @return 如果用户输入的用户名和密码在数据库中验证通过返回User
	 */
	public User login(User user);
	/**
	 * 按照id查询用户
	 * @param id
	 * @return
	 */
	public User findById(Integer id);
	/**
	 * 按照用户名查询用户
	 * @param userName
	 * @return 如果找到返回 true，否则返回false
	 */
	public boolean findByUserName(String userName);

	
	public List<User> query(String userName, Page page);
	
	public long queryCount(String userName);

	public boolean updatePwd(UserUpdatePwd user);
	public boolean update(UserInfoUpdate user);
	
	/**
	 * 设置用户组id
	 * @param userId
	 * @param userGroupId
	 * @return
	 */
//	public boolean setUserGroup4User(int userId, int userGroupId);
}
