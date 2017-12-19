package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.RolesMapper;
import com.yt.cms.mapper.RolesResourceMapper;
import com.yt.cms.model.Resource;
import com.yt.cms.model.Roles;
import com.yt.cms.service.RolesService;
@Service
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesMapper rolesDAO;
	@Autowired
	private RolesResourceMapper rolesResourceDAO;
	@Override
	public boolean save(Roles roles) {
		rolesDAO.insertSelective(roles);
		if(roles.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Roles findById(Integer id) {
		return rolesDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(Roles roles) {
		try {
			rolesDAO.updateByPrimaryKeySelective(roles);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Roles> find(Roles roles) {
		return rolesDAO.query(roles);
	}

	@Override
	public boolean delete(Integer id) {
		try {
			rolesDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeRolesResource(Integer rolesId) {
		try {
			int k = rolesResourceDAO.deleteByRolesId(rolesId);
			System.out.println(k);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Resource> findByRolesId(Integer rolesId) {

		return null;
	}

	
}
