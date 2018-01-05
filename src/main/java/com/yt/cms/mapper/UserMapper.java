package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.User;
import com.yt.cms.model.UserInfoUpdate;
import com.yt.cms.model.UserUpdatePwd;

public interface UserMapper {
	
	List<User> query(@Param("userName") String userName, @Param("page") Page page);
	/**
	 * 由于是like 查询所以这里要用注解明确参数名 
	 * @param userName
	 * @return
	 */
	long queryCount(@Param("userName") String userName);
	
	User findById(Integer id);
	/**
	 * 注册检查用户名是否被注册过
	 * 这里是精确查询所以没有加注解明确参数名
	 * @param userName
	 * @return
	 */
	int findByUserName(String userName);
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	User login(User user);
	
	void insert(User user);

	void delete(Long id);
	/**
	 * 设置用户所属用户组，注册用户必须设置用户组，否则没有权限编辑系统数据
	 * @param id
	 * @param userGroupId
	 */
//	int setUserGroup4User(@Param("id")Integer id, @Param("userGroupId")Integer userGroupId);
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	int updatePwd(UserUpdatePwd user);
	int updateByPrimaryKeySelective(UserInfoUpdate user);
}