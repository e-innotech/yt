package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.ResourceMapper;
import com.yt.cms.model.Resource;
import com.yt.cms.service.ResourceService;
@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceMapper resourceDAO;
	@Override
	public boolean save(Resource resource) {
		resourceDAO.insertSelective(resource);
		if (resource.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Resource findById(Integer id) {
		return resourceDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(Resource resource) {
		try {
			resourceDAO.updateByPrimaryKeySelective(resource);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Resource> find(Resource resource) {
		return resourceDAO.query(resource);
	}

	@Override
	public boolean delete(Integer id) {
		try {
			resourceDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}



	@Override
	public boolean setModule(Integer id, Integer moduleId) {
		try {
			resourceDAO.setResourceModule(id, moduleId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
