package com.yt.cms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.ResourceMapper;
import com.yt.cms.model.Resource;
import com.yt.cms.model.ResourceW;
import com.yt.cms.service.ResourceService;
@Service
public class ResourceServiceImpl implements ResourceService {
	private Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
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
	public List<Resource> find(Resource resource,Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return resourceDAO.query(resource);
	}

	@Override
	public boolean deleteLogicById(Integer id) {
		try {
			int row = resourceDAO.deleteLogicById(id);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	@Cacheable(value = "user", key = "userkey")
	public List<ResourceW> queryResource_W() {
		logger.info("from db....");
		return resourceDAO.queryResource_W();
	}
	
}
