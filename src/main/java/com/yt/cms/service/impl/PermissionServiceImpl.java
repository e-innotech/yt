package com.yt.cms.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.mapper.ResourceMapper;
import com.yt.cms.model.Button;
import com.yt.cms.model.Menu;
import com.yt.cms.model.User;
import com.yt.cms.model.UserGroup;
import com.yt.cms.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private ResourceMapper resourceDAO;

	@Override
	public List<Menu> queryMenu(User user) {
		// 获取全部菜单数据
		List<Menu> list = resourceDAO.queryMenu();
		UserGroup ug = user.getUserGroup();
		if(ug == null) {
			// 跳转到固定页面，提示用户没有权限访问，需要配置权限
			return null;
		}
		// 查询用户所配置的资源数据
		List<Integer> userResource = resourceDAO.queryMenuByUserGroupId(ug.getId());
		// 过滤list并返回该用户的菜单数据
		this.remove(list, userResource);
		return list;
	}

	@Override
	public List<Button> queryMenuButton(Integer userGroupId, Integer menuId) {
		
		return resourceDAO.queryButtonByUserGroupId_menuId(userGroupId, menuId);
	}


	private void remove(List<Menu> list, List<Integer> userResource) {
		Iterator<Menu> it = list.iterator();
		while(it.hasNext()){
			Menu m = it.next();
			// 父节点匹配的上情况下，才会匹配孩子节点
			if(m.getId() != null && userResource.contains(m.getId())) {
				List<Menu> child = m.getChildList();
				if(CollectionUtils.isNotEmpty(child)) {
					this.remove(child, userResource);
				}
			}
			if(m.getId() == null || !userResource.contains(m.getId())) {
				it.remove();
			}
		}
	}
}
