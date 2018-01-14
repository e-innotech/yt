package com.yt.cms.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.front.mapper.AdvertMapper;
import com.yt.cms.front.mapper.SectionMapper;
import com.yt.cms.front.model.Ad;
import com.yt.cms.front.model.Channel;
import com.yt.cms.front.service.WebsiteCommonService;

@Service
public class WebsiteCommonServiceImpl implements WebsiteCommonService {

	@Autowired
	private SectionMapper sectionDAO;
	@Autowired
	private AdvertMapper advertDAO;

	@Override
	public List<Channel> getWebsiteChannel(Integer websiteId) {
		return sectionDAO.query(websiteId);
	}

	@Override
	public List<Ad> getWebsiteTemplateAds(Integer websiteId, Integer templateType) {
		return advertDAO.getWebsiteTemplateAds(websiteId, templateType);
	}


}
