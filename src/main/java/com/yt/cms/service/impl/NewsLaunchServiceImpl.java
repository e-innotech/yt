package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.NewsLaunchMapper;
import com.yt.cms.mapper.NewsPublishMapper;
import com.yt.cms.model.NewsLaunch;
import com.yt.cms.model.NewsLaunchConfig;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.service.NewsLaunchService;
@Service
public class NewsLaunchServiceImpl implements NewsLaunchService {
	
	@Autowired
	private NewsLaunchMapper newsLaunchDAO;
	@Autowired
	private NewsPublishMapper newsPublishDAO;
	
	@Override
	public boolean save(NewsLaunch newsLaunch) {
		newsLaunchDAO.insertSelective(newsLaunch);
		if(newsLaunch.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public NewsLaunch findById(Integer id) {
		return newsLaunchDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(NewsLaunch newsLaunch) {
		try {
			newsLaunchDAO.updateByPrimaryKeySelective(newsLaunch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteLogicById(Integer id) {
		try {
			int row = newsLaunchDAO.deleteLogicById(id);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<NewsLaunch> queryAll(NewsLaunch newsLaunch, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return newsLaunchDAO.query(newsLaunch);
	}

	@Override
	public boolean aduit(NewsLaunch newsLaunch) {
		try {
			if(newsLaunch.getStatus() == Const.ADUIT_PASS) {
				// 上线
				// json 转数组
				List<NewsPublish> newsLaunchs = new ArrayList<NewsPublish>();
				// json string
				String config = newsLaunch.getNewsLaunchConfig();
				List<NewsLaunchConfig> config_list = JSONObject.parseArray(config, NewsLaunchConfig.class);
				for(NewsLaunchConfig list : config_list) {
					List<Integer> channels = list.getChannelId();
					for(Integer channel : channels) {
						NewsPublish news = new NewsPublish();
						news.setWebsiteId(list.getWebId());
						news.setNewsId(newsLaunch.getNewsId());
						news.setChannelId(channel);
						newsLaunchs.add(news);
					}
					
				}
				newsPublishDAO.insertBatch(newsLaunchs);
			}
			// 更新审核信息
			this.update(newsLaunch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
