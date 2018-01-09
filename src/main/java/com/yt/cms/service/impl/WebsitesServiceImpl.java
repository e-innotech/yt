package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.WebsitesChannelMapper;
import com.yt.cms.mapper.WebsitesMapper;
import com.yt.cms.model.Websites;
import com.yt.cms.model.WebsitesChannel;
import com.yt.cms.service.WebsitesService;
@Service
public class WebsitesServiceImpl implements WebsitesService {
	@Autowired
	private WebsitesMapper websitesDAO;
	@Autowired
	private WebsitesChannelMapper websitesChannelDAO;
	@Override
	public boolean save(Websites web) {
		// 新增网站数据
		websitesDAO.insertSelective(web);
		// 新增网站栏目关系表数据
		insertBatchWebChannel(web);
		if(web.getId() > 0) {
			return true;
		}
		return false;
	}

	private void insertBatchWebChannel(Websites web) {
		// 新增网站栏目关系表数据
		List<Integer> channelIds =  web.getChannelIds();
		if(CollectionUtils.isNotEmpty(channelIds)) {
			List<WebsitesChannel> moduleList = new ArrayList<>();
			for(Integer channelid : channelIds) {
				WebsitesChannel webChannel = new WebsitesChannel();
				webChannel.setChannelId(channelid);
				webChannel.setWebsitesId(web.getId());
				moduleList.add(webChannel);
			}
			websitesChannelDAO.insertBatch(moduleList);
		}
	}

	@Override
	public Websites findById(Integer id) {
		return websitesDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<Websites> queryAll(Websites web, Page page) {
		return websitesDAO.query(web,page);
	}

	@Override
	public long queryCount(Websites web) {
		return websitesDAO.queryCount(web);
	}

	@Override
	// 事务管理,最后一个表有外键，最后执行失败，但是其他2个执行成功，
	// TODO
	public boolean update(Websites web) {
		try {
			int row = websitesDAO.updateByPrimaryKeySelective(web);
			// 先删除网站栏目关系数据
			websitesChannelDAO.deleteByWebId(web.getId());
			// 新增网站栏目关系表数据
			insertBatchWebChannel(web);
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
			int row = websitesDAO.deleteLogicById(id);
			if(row == 1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
