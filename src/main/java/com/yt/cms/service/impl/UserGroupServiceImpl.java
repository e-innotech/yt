package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return userGroupDAO.query(groupName,page);
	}
	

	@Override
	public long queryCount(String groupName) {
		return userGroupDAO.queryCount(groupName);
	}

	@Override
	public List<UserGroup> queryAll() {
		return userGroupDAO.queryAll();
	}

	@Override
	public boolean update(UserGroup userGroup) {
		try {
			int row = userGroupDAO.updateByPrimaryKeySelective(userGroup);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteLogicById(Integer id) {
		try {
			int row = userGroupDAO.deleteLogicById(id);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
