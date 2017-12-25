package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.mapper.UserGroupRolesMapper;
import com.yt.cms.model.UserGroupRoles;
import com.yt.cms.service.UserGroupRolesService;

@Service
public class UserGroupRolesServiceImpl implements UserGroupRolesService {

	@Autowired
	private UserGroupRolesMapper userGroupRolesDAO;

	@Override
	public boolean save(List<UserGroupRoles> userGroupRoles) {
		try {
			userGroupRolesDAO.insertBatch(userGroupRoles);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Integer userGroupId, Integer[] old_rolesIds, Integer[] new_rolesIds) {
		// 在old中找出不在new 中的元素
		try {
			List<Integer> delList = CollectionUtils.find(old_rolesIds, new_rolesIds);
			// 在new中找出不在old 中的元素
			List<Integer> addList = CollectionUtils.find(new_rolesIds, old_rolesIds);

			// 删除解除关系的数据
			if(CollectionUtils.isNotEmpty(delList)) {
				userGroupRolesDAO.deleteByUserGroupIdRolesIds(userGroupId, delList);
			}
			// 新增新建立关系的数据
			List<UserGroupRoles> moduleList = new ArrayList<UserGroupRoles>();
			for (Integer id : addList) {
				UserGroupRoles ur = new UserGroupRoles();
				ur.setUserGroupId(userGroupId);
				ur.setRolesId(id);
				moduleList.add(ur);
			}
			if(CollectionUtils.isNotEmpty(moduleList)) {
				userGroupRolesDAO.insertBatch(moduleList);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
