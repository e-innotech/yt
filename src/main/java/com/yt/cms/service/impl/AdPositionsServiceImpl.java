package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.AdPositionsMapper;
import com.yt.cms.model.AdPositions;
import com.yt.cms.service.AdPositionsService;
@Service
public class AdPositionsServiceImpl implements AdPositionsService {
	@Autowired
	private AdPositionsMapper adpositionDAO;
	@Override
	public boolean save(AdPositions adPositions) {
		adpositionDAO.insertSelective(adPositions);
		if(adPositions.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public AdPositions findById(Integer id) {
		return adpositionDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<AdPositions> queryAll(AdPositions adPositions,Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return adpositionDAO.query(adPositions);
	}

	@Override
	public boolean update(AdPositions adPositions) {
		try {
			adpositionDAO.updateByPrimaryKeySelective(adPositions);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			adpositionDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
