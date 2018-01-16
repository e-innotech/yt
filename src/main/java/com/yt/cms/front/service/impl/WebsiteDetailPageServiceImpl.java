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
	public Article getWebsiteChannelNewsDetail(Integer publishId) {
		return articleDAO.getWebsiteChannelNewsDetail(publishId);
	}

	@Override
	public List<Comments> getArticleComments(Integer publishId,Page page) {
		return articleDAO.getArticleComments(publishId, page);
	}
	
	@Override
	public long getArticleCommentNum(Integer publishId) {
		return articleDAO.getArticleCommentsCount(publishId);
	}

	@Override
	public long getArticlePV(Integer websiteId,Integer articleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
