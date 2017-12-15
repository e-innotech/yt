package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.RolesResourceMapper;
import com.yt.cms.model.RolesResource;
import com.yt.cms.service.RolesResourceService;
@Service
public class RolesResourceServiceImpl implements RolesResourceService {
	@Autowired
	private RolesResourceMapper rolesResourceDAO;
	@Override
	public boolean save(RolesResource rolesResource) {
		rolesResourceDAO.insert(rolesResource);
		if (rolesResource.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public RolesResource findById(Integer id) {
		return rolesResourceDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<RolesResource> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(RolesResource rolesResource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RolesResource> find(RolesResource rolesResource) {
		// TODO Auto-generated method stub
		return null;
	}

}
