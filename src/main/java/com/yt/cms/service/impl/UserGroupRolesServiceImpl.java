package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.UserGroupRolesMapper;
import com.yt.cms.model.Roles;
import com.yt.cms.model.UserGroupRoles;
import com.yt.cms.service.UserGroupRolesService;
@Service
public class UserGroupRolesServiceImpl implements UserGroupRolesService {

	@Autowired
	private UserGroupRolesMapper userGroupRolesDAO;
	@Override
	public boolean save(UserGroupRoles userGroupRoles) {
		userGroupRolesDAO.insert(userGroupRoles);
		if(userGroupRoles.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Roles> findById(Integer id) {
		UserGroupRoles userGroupRoles = userGroupRolesDAO.selectByPrimaryKey(id);
		return null;
	}

	@Override
	public List<UserGroupRoles> queryAll() {
		return userGroupRolesDAO.query();
	}

	@Override
	public boolean update(UserGroupRoles userGroupRoles) {
		try {
			userGroupRolesDAO.updateByPrimaryKey(userGroupRoles);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
