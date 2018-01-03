package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.Button;
import com.yt.cms.model.MenuLeve1;
import com.yt.cms.model.Resource;
import com.yt.cms.model.User;

/**
 * 权限服务
 * 主要是面向前端的查询服务
 * @author admin
 *
 */
public interface PermissionService {
	/**
	 * 查询左侧的菜单接口
	 * 依据当前用户的角色展示菜单数据
	 * @param user
	 * @return
	 */
	public List<MenuLeve1> queryMenu(User user);
	/**
	 * 查询用户角色名
	 * @param UserGroupId
	 * @return
	 */
	public String queryRolesName(Integer userGroupId);
	/**
	 * 该方法做成切面，对于列表页的资源请求都执行该方法
	 * 用户点击左侧菜单查询该用户拥有的按钮资源
	 * @param userGroupId
	 * @param menuId
	 * @return
	 */
	public List<Button> queryMenuButton(Integer userGroupId, Integer menuId);
	/**
	 * 判断用户是否有权限访问'
	 * @param userGroupId
	 * @param userId
	 * @param requestURL
	 * @return
	 */
	public boolean hasPermission(Integer userGroupId,Integer userId, String requestURL);
	/**
	 * 查询改用可访问的所有资源权限
	 * @param userId
	 * @return
	 */
	public List<Resource> getResources(Integer userId);

}
