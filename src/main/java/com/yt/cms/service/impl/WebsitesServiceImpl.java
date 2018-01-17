package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yt.cms.common.CollectionUtils;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.WebsiteTemplateMapper;
import com.yt.cms.mapper.WebsitesChannelMapper;
import com.yt.cms.mapper.WebsitesMapper;
import com.yt.cms.model.WebsiteTemplate;
import com.yt.cms.model.Websites;
import com.yt.cms.model.WebsitesChannel;
import com.yt.cms.service.WebsitesService;
@Service
public class WebsitesServiceImpl implements WebsitesService {
	@Autowired
	private WebsitesMapper websitesDAO;
	@Autowired
	private WebsitesChannelMapper websitesChannelDAO;
	@Autowired
	private WebsiteTemplateMapper websiteTemplateDAO;
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean save(Websites web) {
		// 新增网站数据
		websitesDAO.insertSelective(web);
		// 新增网站栏目关系表数据
		insertBatchWebChannel(web,false);
		// 新增网站模板数据
		List<WebsiteTemplate> webTemplates = web.getWebTemplates();
		if(CollectionUtils.isNotEmpty(webTemplates)) {
			for(WebsiteTemplate template : webTemplates) {
				template.setWebsites(web);
			}
			websiteTemplateDAO.insertBatch(webTemplates);
		}
		if(web.getId() > 0) {
			return true;
		}
		return false;
	}

	private void insertBatchWebChannel(Websites web, boolean isUpdate) {
		List<Integer> channelIds =  web.getChannelIds();
		if(CollectionUtils.isNotEmpty(channelIds)) {
			// 先删除网站栏目关系数据
			if(isUpdate) {
				websitesChannelDAO.deleteByWebId(web.getId());
			}
			List<WebsitesChannel> moduleList = new ArrayList<>();
			for(Integer channelid : channelIds) {
				WebsitesChannel webChannel = new WebsitesChannel();
				webChannel.setChannelId(channelid);
				webChannel.setWebsitesId(web.getId());
				moduleList.add(webChannel);
			}
			// 新增网站栏目关系表数据
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
	@Transactional(rollbackFor=Exception.class)
	public boolean update(Websites web) {
		try {
			int row = websitesDAO.updateByPrimaryKeySelective(web);
			// 网站栏目关系表数据
			insertBatchWebChannel(web,true);
			// 修改模板路径，也只能修改模板路径
			List<WebsiteTemplate> webTemplates = web.getWebTemplates();
			if(CollectionUtils.isNotEmpty(webTemplates)) {
				websiteTemplateDAO.updateBatch(webTemplates);
			}
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

	@Override
	public List<WebsiteTemplate> queryWebsiteTemplate(WebsiteTemplate webTemplate, Page page) {
		return websiteTemplateDAO.query(webTemplate, page);
	}

	@Override
	public long queryWebsiteTemplateCount(WebsiteTemplate webTemplate) {
		return websiteTemplateDAO.queryCount(webTemplate);
	}


}
