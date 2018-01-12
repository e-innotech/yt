package com.yt.cms.front.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.Ad;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.model.Comments;
import com.yt.cms.front.service.WebsiteChannelService;
import com.yt.cms.model.Channel;

@Service
public class WebsiteChannelServiceImpl implements WebsiteChannelService {

	@Override
	public List<Channel> getWebsiteChannel(Integer websiteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getWebsiteChannelNews(Integer websiteId, Integer channelId,Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ad> getWebsiteTemplateAds(Integer websiteId, Integer templateType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comments> getArticleComments(Integer articleId,Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getArticleCommentNum(Integer articleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getArticlePV(Integer articleId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
