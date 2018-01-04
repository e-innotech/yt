package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.RolesMapper;
import com.yt.cms.mapper.RolesResourceMapper;
import com.yt.cms.model.Roles;
import com.yt.cms.model.page.RolesPage;
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
			int row = rolesDAO.updateByPrimaryKeySelective(roles);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Roles> find(RolesPage page) {
		return rolesDAO.query(page);
	}

	@Override
	public Integer findCount(RolesPage page) {
		return rolesDAO.queryCount(page);
	}

	@Override
	public boolean deleteLogicById(Integer id) {
		try {
			int row = rolesDAO.deleteLogicById(id);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeRolesResource(Integer rolesId) {
		try {
			int k = rolesResourceDAO.deleteByRolesId(rolesId);
			if(k > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
