package com.yt.cms.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.Page;
import com.yt.cms.front.mapper.ArticleMapper;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.model.Comments;
import com.yt.cms.front.service.WebsiteDetailPageService;

@Service
public class WebsiteDetailPageServiceImpl implements WebsiteDetailPageService {

	@Autowired
	private ArticleMapper articleDAO;
	@Override
	public Article getWebsiteChannelNewsDetail(Integer websiteId, Integer channelId, Integer articleId) {
		return articleDAO.getWebsiteChannelNewsDetail(websiteId, channelId, articleId);
	}

	@Override
	public List<Comments> getArticleComments(Integer websiteId,Integer articleId,Page page) {
		return articleDAO.getArticleComments(websiteId,articleId, page);
	}
	
	@Override
	public long getArticleCommentNum(Integer websiteId,Integer articleId) {
		return articleDAO.getArticleCommentsCount(websiteId,articleId);
	}

	@Override
	public long getArticlePV(Integer websiteId,Integer articleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getArticleCommentsTotal(Integer websiteId, Integer articleId) {
		return articleDAO.getArticleCommentsTotal(websiteId, articleId);
	}
	
}
