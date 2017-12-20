package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.mapper.WebsitesNavigationBarMapper;
import com.yt.cms.model.WebsitesNavigationBar;
import com.yt.cms.service.WebsitesNavigationBarService;
@Service
public class WebsitesNavigationBarServiceImpl implements WebsitesNavigationBarService {
	@Autowired
	private WebsitesNavigationBarMapper websitesBarDAO;
	
	@Override
	public boolean save(List<WebsitesNavigationBar> moduleList) {
		try {
			websitesBarDAO.insertBatch(moduleList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Integer websitesId, Integer[] old_navigationBarIds, Integer[] new_navigationBarIds) {
		// 在old中找出不在new 中的元素
		try {
			List<Integer> delList = CollectionUtils.find(old_navigationBarIds, new_navigationBarIds);
			// 在new中找出不在old 中的元素
			List<Integer> addList = CollectionUtils.find(new_navigationBarIds, old_navigationBarIds);
					
			// 删除解除关系的数据
			websitesBarDAO.deleteByWebsitesId(websitesId, delList);
			// 新增新建立关系的数据
			List<WebsitesNavigationBar> moduleList = new ArrayList<WebsitesNavigationBar>();
			for(Integer id : addList) {
				WebsitesNavigationBar bar = new WebsitesNavigationBar();
				bar.setNavigationBarId(id);
				bar.setWebsitesId(websitesId);
				moduleList.add(bar);
			}
			websitesBarDAO.insertBatch(moduleList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	


}
