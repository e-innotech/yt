package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.ModuleMapper;
import com.yt.cms.model.Module;
import com.yt.cms.service.ModuleService;
@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleMapper moduleDAO;
	
	@Override
	public boolean save(Module module) {
		moduleDAO.insert(module);
		if(module.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Module findById(Integer id) {
		return moduleDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<Module> queryAll(Module module) {
		return moduleDAO.query(module);
	}

	@Override
	public boolean update(Module module) {
		try {
			moduleDAO.updateByPrimaryKeySelective(module);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Module> find(Module module) {
		return moduleDAO.query(module);
	}

}
