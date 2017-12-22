package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.NewsPublishMapper;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.model.NewsPublishLine;
import com.yt.cms.service.NewsPublishService;
@Service
public class NewsPublishServiceImpl implements NewsPublishService {
	@Autowired
	private NewsPublishMapper newsPublishDAO;
	@Override
	public boolean save(List<NewsPublish> newsLaunchs) {
		try {
			newsPublishDAO.insertBatch(newsLaunchs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public NewsPublishLine findById(Integer id) {
		return newsPublishDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(NewsPublish newsPublish) {
		try {
			newsPublishDAO.updateByPrimaryKeySelective(newsPublish);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<NewsPublish> query(NewsPublish newsPublish) {
		return newsPublishDAO.query(newsPublish);
	}

}
