package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.mapper.RolesResourceMapper;
import com.yt.cms.model.RolesResource;
import com.yt.cms.service.RolesResourceService;


@Service
public class RolesResourceServiceImpl implements RolesResourceService {
	
	private Logger logger = LoggerFactory.getLogger(RolesResourceServiceImpl.class);
	
	@Autowired
	private RolesResourceMapper rolesResourceDAO;

	@Override
	public boolean save(List<RolesResource> rolesResource) {
		try {
			int k = rolesResourceDAO.insertBatch(rolesResource);
			logger.info("insertBatch count is :{}" + k );
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(Integer rolesId, Integer[] old_resourceIds, Integer[] new_resourceIds) {

		// 在old中找出不在new 中的元素
		try {
			List<Integer> delList = CollectionUtils.find(old_resourceIds, new_resourceIds);
			// 在new中找出不在old 中的元素
			List<Integer> addList = CollectionUtils.find(new_resourceIds, old_resourceIds);

			// 删除解除关系的数据
			if (CollectionUtils.isNotEmpty(delList)) {
				rolesResourceDAO.deleteByRolesIdResourceIds(rolesId, delList);
			}
			// 新增新建立关系的数据
			List<RolesResource> moduleList = new ArrayList<RolesResource>();
			for (Integer id : addList) {
				RolesResource rr = new RolesResource();
				rr.setResourceId(id);
				rr.setRolesId(rolesId);
				moduleList.add(rr);
			}
			if (CollectionUtils.isNotEmpty(moduleList)) {
				rolesResourceDAO.insertBatch(moduleList);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
