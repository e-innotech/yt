package com.yt.cms.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.common.Const;
import com.yt.cms.mapper.PermissionMapper;
import com.yt.cms.model.Button;
import com.yt.cms.model.MenuLeve1;
import com.yt.cms.model.MenuLeve2;
import com.yt.cms.model.Resource;
import com.yt.cms.model.User;
import com.yt.cms.model.UserGroup;
import com.yt.cms.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {


	@Autowired
	private PermissionMapper permissionDAO;
	@Override
	public List<MenuLeve1> queryMenu(User user) {
		// 获取全部菜单数据
		List<MenuLeve1> list = permissionDAO.queryMenu();
		UserGroup ug = user.getUserGroup();
		if(ug == null) {
			// 跳转到固定页面，提示用户没有权限访问，需要配置权限
			return null;
		}
		// 超级管理员组
		if(ug.getId().intValue() == Const.SUPER_ADMIN_USER_GROUP.intValue()) {
			return list;
		}
		// 查询用户所配置的资源数据
		List<Integer> userResource = permissionDAO.queryMenuByUserGroupId(ug.getId());
		// 过滤list并返回该用户的菜单数据
		this.remove(list, userResource);
		return list;
	}

	@Override
	public List<Button> queryMenuButton(Integer userGroupId, Integer menuId) {
		if(userGroupId.intValue() == Const.SUPER_ADMIN_USER_GROUP.intValue()) {
			return  permissionDAO.queryMenuButton(menuId);
		}
		return permissionDAO.queryMenuButtonByUserGroupId(userGroupId, menuId);
	}


	@Override
	public String queryRolesName(Integer userGroupId) {
		return permissionDAO.queryRolesName(userGroupId);
	}
	

	@Override
	public boolean hasPermission(Integer userGroupId, Integer userId, String requestURL) {
		// 超级管理员
		if(Const.SUPER_ADMIN_USER_GROUP.intValue() == userGroupId.intValue()) {
			return true;
		}
		List<Resource> list = this.getResources(userId);
		if(CollectionUtils.isNotEmpty(list)) {
			for(Resource r : list) {
				if(requestURL.equals(r.getUri())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Resource> getResources(Integer userId) {
		User user = permissionDAO.getUserPermissionInfo(userId);
		if(user != null && user.getUserGroup() != null && user.getUserGroup().getRoles() != null) {
			return user.getUserGroup().getRoles().getResource();
		}
		return null;
	}

	private void remove3(List<Button> list, List<Integer> userResource) {
		Iterator<Button> it = list.iterator();
		while(it.hasNext()){
			Button m = it.next();
			if(m.getId() == null || !userResource.contains(m.getId())) {
				it.remove();
			}
		}
	}
	
	private void remove2(List<MenuLeve2> list, List<Integer> userResource) {
		Iterator<MenuLeve2> it = list.iterator();
		while(it.hasNext()){
			MenuLeve2 m = it.next();
			// 父节点匹配的上情况下，才会匹配孩子节点
			if(m.getId() != null && userResource.contains(m.getId())) {
				List<Button> child = m.getButtons();
				if(CollectionUtils.isNotEmpty(child)) {
					this.remove3(child, userResource);
				}
			}
			if(m.getId() == null || !userResource.contains(m.getId())) {
				it.remove();
			}
		}
	}
	private void remove(List<MenuLeve1> list, List<Integer> userResource) {
		Iterator<MenuLeve1> it = list.iterator();
		while(it.hasNext()){
			MenuLeve1 m = it.next();
			// 父节点匹配的上情况下，才会匹配孩子节点
			if(m.getId() != null && userResource.contains(m.getId())) {
				List<MenuLeve2> child = m.getNodes();
				if(CollectionUtils.isNotEmpty(child)) {
					//
					this.remove2(child, userResource);
				}
			}
			if(m.getId() == null || !userResource.contains(m.getId())) {
				it.remove();
			}
		}
	}
}
