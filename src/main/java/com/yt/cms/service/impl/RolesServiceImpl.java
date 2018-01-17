package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.mapper.RolesMapper;
import com.yt.cms.mapper.RolesResourceMapper;
import com.yt.cms.model.Roles;
import com.yt.cms.model.RolesResource;
import com.yt.cms.model.page.RolesPage;
import com.yt.cms.service.RolesService;
@Service
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesMapper rolesDAO;
	@Autowired
	private RolesResourceMapper rolesResourceDAO;
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean save(Roles roles) {
		rolesDAO.insertSelective(roles);
		// 新增角色资源关系
		addRolesResource(roles,false); 
		if(roles.getId() > 0) {
			return true;
		}
		return false;
	}
	private void addRolesResource(Roles roles, boolean isUpdate) {
		List<Integer> resourceIds = roles.getResourceIds();
		if(CollectionUtils.isNotEmpty(resourceIds)) {
			// 删除角色资源关系
			if(isUpdate) {
				rolesResourceDAO.deleteByRolesId(roles.getId());
			}
			// 角色资源关系
			List<RolesResource> rolesResource = new ArrayList<>();
			for(Integer resourceId : resourceIds) {
				RolesResource rr = new RolesResource();
				rr.setResourceId(resourceId);
				rr.setRolesId(roles.getId());
				rolesResource.add(rr);
			}
			rolesResourceDAO.insertBatch(rolesResource);
		}
	}
	@Override
	public Roles findById(Integer id) {
		return rolesDAO.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean update(Roles roles) {
		try {
			if(StringUtils.hasText(roles.getRoleName()) || StringUtils.hasText(roles.getRemark())) {
				rolesDAO.updateByPrimaryKeySelective(roles);
			}
			// 在重新写入角色资源关系数据
			addRolesResource(roles,true);
			return true;
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
