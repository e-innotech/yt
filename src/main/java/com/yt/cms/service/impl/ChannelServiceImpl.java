package com.yt.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Const;
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
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
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
	public boolean deleteLogicById(Integer id) {
		try {
			Channel record = new Channel();
			record.setId(id);
			record.setIsDel(Const.DELETE_FLAG);
			record.setDelDate(new Date());
			int row = channelDAO.updateByPrimaryKeySelective(record);
			if(row == 1) {
				return true;
			}
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
