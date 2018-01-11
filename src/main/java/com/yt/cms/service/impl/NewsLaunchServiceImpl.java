package com.yt.cms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yt.cms.common.CollectionUtils;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.ChannelMapper;
import com.yt.cms.mapper.NewsLaunchMapper;
import com.yt.cms.mapper.NewsMapper;
import com.yt.cms.mapper.NewsPublishMapper;
import com.yt.cms.mapper.WebsitesMapper;
import com.yt.cms.model.Channel;
import com.yt.cms.model.News;
import com.yt.cms.model.NewsLaunch;
import com.yt.cms.model.NewsLaunchConfig;
import com.yt.cms.model.NewsLaunchWebChannelConfig;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.model.Websites;
import com.yt.cms.service.NewsLaunchService;
@Service
public class NewsLaunchServiceImpl implements NewsLaunchService {
	
	@Autowired
	private NewsLaunchMapper newsLaunchDAO;
	@Autowired
	private NewsPublishMapper newsPublishDAO;
	@Autowired
	private NewsMapper newsDAO;
	@Autowired
	private ChannelMapper channelDAO;
	@Autowired
	private WebsitesMapper websiteDAO;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
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
		Set<Integer> webIds = new HashSet<>();
		Set<Integer> channelIds = new HashSet<>();
		List<Websites> websites = null;
		List<Channel> channels = null;
		// 存储数据库中查询到的网站信息 key:website.id value:website
		Map<Integer, Websites> websitesMap = new HashMap<Integer, Websites>();
		// 存储数据库中查询到的栏目信息 key:channel.id value:channel
		Map<Integer, Channel> channelMap = new HashMap<Integer, Channel>();
		for(NewsLaunch launch : list) {
			String json = launch.getNewsLaunchConfig();
			try {
				if(!StringUtils.isEmpty(json)) {
					List<NewsLaunchConfig> config_list = JSONObject.parseArray(json, NewsLaunchConfig.class);
					for(NewsLaunchConfig config : config_list) {
						// 网站id
						Integer webId = config.getWebsiteId();
						List<Integer> channel = config.getChannelId();
						webIds.add(webId);
						channelIds.addAll(channel);
					}
				}
			
			} catch (Exception e) {
				// 此数据配置错误，忽略
				e.printStackTrace();
			}
		}
		if(CollectionUtils.isNotEmpty(webIds)) {
			websites = websiteDAO.queryByIds(CollectionUtils.changeForSet(webIds));
			// 将数据库中的数据存入 websiteMap
			if(CollectionUtils.isNotEmpty(websites)) {
				for(Websites w : websites) {
					websitesMap.put(w.getId(), w);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(channelIds)) {
			channels = channelDAO.queryByIds(CollectionUtils.changeForSet(channelIds));
			// 将数据库中的数据存入 websiteMap
			if(CollectionUtils.isNotEmpty(channels)) {
				for(Channel c : channels) {
					channelMap.put(c.getId(), c);
				}
			}
		}
		// 找投放对应的网站和栏目
		for(NewsLaunch launch : list) {
			// 初始化稿件投放关系网站名和栏目名，但还没有赋值
			List<NewsLaunchWebChannelConfig> webChannelConfig = new ArrayList<>();
			String json = launch.getNewsLaunchConfig();
			try {
				if(!StringUtils.isEmpty(json)) {
					List<NewsLaunchConfig> config_list = JSONObject.parseArray(json, NewsLaunchConfig.class);
					for(NewsLaunchConfig config : config_list) {
						NewsLaunchWebChannelConfig configvo = new NewsLaunchWebChannelConfig();
						// 网站id
						Integer webId = config.getWebsiteId();
						List<Integer> channel = config.getChannelId();
						Websites web = websitesMap.get(webId);
						configvo.setWebsite(web);
						if(CollectionUtils.isNotEmpty(channel)) {
							List<Channel> webchannels = new ArrayList<>(); 
							for(Integer i : channel) {
								Channel c = channelMap.get(i);
								webchannels.add(c);
							}
							configvo.setChannels(webchannels);
						}
						webChannelConfig.add(configvo);
					}
				}
				
			} catch (Exception e) {
				// 此数据配置错误，忽略
				e.printStackTrace();
			}
			launch.setWebChannelConfig(webChannelConfig);
		}
		return list;
	}

	@Override
	public long queryCount(NewsLaunch newsLaunch) {
		return newsLaunchDAO.queryCount(newsLaunch);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean aduit(NewsLaunch newsLaunch) {
		try {
//			News record = new News();
			if(newsLaunch.getStatus() == Const.ADUIT_PASS) {
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
				// 上线
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
