package com.yt.cms.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.Page;
import com.yt.cms.front.mapper.WebsiteHomeMapper;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.service.WebsiteHomeService;

@Service
public class WebsiteHomeServiceImpl implements WebsiteHomeService {

	@Autowired
	private WebsiteHomeMapper websiteHomeDAO;
	@Override
	public List<Article> getWebsiteHomeByWeight(Integer websiteId, Integer homeWeight, Page page) {
		return websiteHomeDAO.getWebsiteHomeByWeight(websiteId, homeWeight, page);
	}

	@Override
	public long getWebsiteHomeByWeightCount(Integer websiteId, Integer homeWeight) {
		return websiteHomeDAO.getWebsiteHomeByWeightCount(websiteId, homeWeight);
	}


}
