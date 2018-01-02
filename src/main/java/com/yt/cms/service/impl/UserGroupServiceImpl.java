package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.UserGroupMapper;
import com.yt.cms.model.UserGroup;
import com.yt.cms.service.UserGroupService;
@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupMapper userGroupDAO;
	
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
	public List<UserGroup> query(String groupName, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return userGroupDAO.query(groupName);
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
	public boolean updateRolesByUserGroupId(Integer userGroupId, Integer rolesId) {
		try {

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteLogicById(Integer id) {
		try {
			userGroupDAO.deleteLogicById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
