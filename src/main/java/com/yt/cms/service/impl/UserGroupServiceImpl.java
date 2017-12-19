package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.UserGroupMapper;
import com.yt.cms.mapper.UserGroupRolesMapper;
import com.yt.cms.model.UserGroup;
import com.yt.cms.service.UserGroupService;
@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupMapper userGroupDAO;
	@Autowired
	private UserGroupRolesMapper userGroupRolesDAO;
	@Override
	public boolean save(UserGroup userGroup) {
		userGroupDAO.insertSelective(userGroup);
		if (userGroup.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserGroup findById(Integer id) {
		return userGroupDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<UserGroup> query(UserGroup userGroup) {
		return userGroupDAO.query(userGroup);
	}
	

	@Override
	public List<UserGroup> queryAll() {
		return userGroupDAO.queryAll();
	}

	@Override
	public boolean update(UserGroup userGroup) {
		try {
			userGroupDAO.updateByPrimaryKeySelective(userGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteByUserGroupId(Integer userGroupId) {
		try {
			userGroupRolesDAO.deleteByUserGroupId(userGroupId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			userGroupDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
