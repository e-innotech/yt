package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.NewsLaunchMapper;
import com.yt.cms.mapper.NewsMapper;
import com.yt.cms.mapper.NewsPublishMapper;
import com.yt.cms.model.News;
import com.yt.cms.model.NewsLaunch;
import com.yt.cms.model.NewsLaunchConfig;
import com.yt.cms.model.NewsLaunchWebChannelConfig;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.service.NewsLaunchService;
@Service
public class NewsLaunchServiceImpl implements NewsLaunchService {
	
	@Autowired
	private NewsLaunchMapper newsLaunchDAO;
	@Autowired
	private NewsPublishMapper newsPublishDAO;
	@Autowired
	private NewsMapper newsDAO;
	@Override
	public boolean save(NewsLaunch newsLaunch) {
		// 新增launch表
		newsLaunchDAO.insertSelective(newsLaunch);
		// 修改news 表的状态status=1
		News record = new News();
		record.setId(newsLaunch.getNewsId());
		record.setStatus(new Integer(1));
		newsDAO.updateStatusByPrimaryKey(record);
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
			int row = newsLaunchDAO.updateByPrimaryKeySelective(newsLaunch);
			if(row == 1) {
				return true;
			}
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
		
		List<NewsLaunch> list = newsLaunchDAO.query(newsLaunch,page);
		for(NewsLaunch launch : list) {
			String json = launch.getNewsLaunchConfig();
			// 将json 转map
			try {
				NewsLaunchConfig config = JSON.parseObject(json, NewsLaunchConfig.class);
				// 网站id
				Integer webId = config.getWebsiteId();
				List<Integer> channelId = config.getChannelId();
				List<NewsLaunchWebChannelConfig> webChannelConfig = new ArrayList<NewsLaunchWebChannelConfig>();
				launch.setWebChannelConfig(webChannelConfig);
			} catch (Exception e) {
				// 此数据配置错误，忽略
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public long queryCount(NewsLaunch newsLaunch) {
		return newsLaunchDAO.queryCount(newsLaunch);
	}

	@Override
	public boolean aduit(NewsLaunch newsLaunch) {
		try {
//			News record = new News();
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
						news.setWebsiteId(list.getWebsiteId());
						news.setNewsId(newsLaunch.getNewsId());
						news.setChannelId(channel);
						news.setNewsLaunch(newsLaunch);
						newsLaunchs.add(news);
					}
				}
				newsPublishDAO.insertBatch(newsLaunchs);
//				record.setId(newsLaunch.getNewsId());
//				record.setStatus(Const.LAUNCH_PASS);
			} /*else if(newsLaunch.getStatus() == Const.ADUIT_NO_PASS){
				// 更新news 表status 状态
				record.setId(newsLaunch.getNewsId());
				record.setStatus(Const.LAUNCH_NO_PASS);
			}
			newsDAO.updateStatusByPrimaryKey(record);*/
			// 更新审核信息
			newsLaunchDAO.updateStatusByPrimaryKey(newsLaunch);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
