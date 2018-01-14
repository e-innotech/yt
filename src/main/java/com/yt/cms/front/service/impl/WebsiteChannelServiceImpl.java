package com.yt.cms.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.Page;
import com.yt.cms.front.mapper.ArticleMapper;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.service.WebsiteChannelService;

@Service
public class WebsiteChannelServiceImpl implements WebsiteChannelService {
	@Autowired
	private ArticleMapper articleDAO;

	@Override
	public List<Article> getWebsiteChannelNews(Integer websiteId, Integer channelId,Page page) {
		return articleDAO.getWebsiteChannelNews(websiteId, channelId, page);
	}

	@Override
	public long getWebsiteChannelNewsCount(Integer websiteId, Integer channelId) {
		return articleDAO.getWebsiteChannelNewsCount(websiteId, channelId);
	}

}
