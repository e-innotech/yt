package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.NewsMapper;
import com.yt.cms.model.News;
import com.yt.cms.service.NewsService;
@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsMapper newsDAO;
	
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
	public List<News> queryAll(News news, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
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
	public boolean deleteLogicById(Integer id) {
		try {
			int row = newsDAO.deleteLogicById(id);
			if(row == 1) {
				return true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	

}
