package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.NewsMapper;
import com.yt.cms.mapper.NewsWebsitesMapper;
import com.yt.cms.model.News;
import com.yt.cms.service.NewsService;
@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsMapper newsDAO;
	@Autowired
	private NewsWebsitesMapper newsWebsitesDAO;
	
	@Override
	public boolean save(News news) {
		newsDAO.insertSelective(news);
		if(news.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public News findById(Integer id) {
		return newsDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<News> queryAll(News news) {
		return newsDAO.query(news);
	}

	@Override
	public boolean update(News news) {
		try {
			newsDAO.updateByPrimaryKeySelective(news);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			newsDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean launch(Integer newsId,List<Integer> websitesId) {
		try {
			newsWebsitesDAO.insertBatch(newsId, websitesId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
