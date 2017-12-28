package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.ChannelMapper;
import com.yt.cms.model.Channel;
import com.yt.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelMapper channelDAO;
	@Override
	public boolean save(Channel bar) {
		channelDAO.insertSelective(bar);
		if(bar.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Channel findById(Integer id) {
		return channelDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<Channel> queryAll(Channel bar,Page page) {
		PageHelper.offsetPage(page.getPageNum(), page.getPageSize());
		return channelDAO.query(bar);
	}

	@Override
	public boolean update(Channel bar) {
		try {
			channelDAO.updateByPrimaryKeySelective(bar);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			channelDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean findByChannelName(String channelName) {
		int i = channelDAO.findChannelName(channelName);
		if(i > 0) {
			return true;
		}
		return false;
	}

}
