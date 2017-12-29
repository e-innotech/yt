package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.YtcmsConfigMapper;
import com.yt.cms.model.YtcmsConfig;
import com.yt.cms.service.YtcmsConfigService;
@Service
public class YtcmsConfigServiceImpl implements YtcmsConfigService {
	@Autowired
	private YtcmsConfigMapper configDAO;
	@Override
	public boolean save(YtcmsConfig config) {
		configDAO.insertSelective(config);
		if(config.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public YtcmsConfig findById(Integer id) {
		return configDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<YtcmsConfig> queryAll(YtcmsConfig config,Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return configDAO.query(config);
	}

	@Override
	public boolean update(YtcmsConfig config) {
		 try {
			configDAO.updateByPrimaryKeySelective(config);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
