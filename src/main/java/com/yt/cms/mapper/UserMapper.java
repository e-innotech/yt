package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.User;

public interface UserMapper {
	
	List<User> query();
	
	User findById(Integer id);
	/**
	 * 注册检查用户名是否被注册过
	 * @param userName
	 * @return
	 */
	int findByUserName(String userName);
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	int login(User user);
	
	void insert(User user);

	void update(User user);

	void disableOrEnable(User user);
	
	void delete(Long id);
	/**
	 * 设置用户所属用户组，注册用户必须设置用户组，否则没有权限编辑系统数据
	 * @param id
	 * @param userGroupId
	 */
	void setUserGroup4User(@Param("id")Integer id, @Param("userGroupId")Integer userGroupId);
}