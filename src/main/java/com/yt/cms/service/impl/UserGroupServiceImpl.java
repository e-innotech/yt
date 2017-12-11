package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.model.UserGroup;
import com.yt.cms.service.UserGroupService;
@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupService userGroupService;

	@Override
	public boolean save(UserGroup userGroup) {
		userGroupService.save(userGroup);
		if (userGroup.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserGroup findById(Integer id) {
		return userGroupService.findById(id);
	}

	@Override
	public List<UserGroup> query() {
		return userGroupService.query();
	}

	@Override
	public boolean update(UserGroup userGroup) {
		try {
			userGroupService.update(userGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
