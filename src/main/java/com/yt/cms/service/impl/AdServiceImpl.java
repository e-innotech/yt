package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.AdMapper;
import com.yt.cms.model.Ad;
import com.yt.cms.service.AdService;
@Service
public class AdServiceImpl implements AdService {
	@Autowired
	private AdMapper adDAO;
	@Override
	public boolean save(Ad ad) {
		adDAO.insertSelective(ad);
		if(ad.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Ad findById(Integer id) {
		return adDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<Ad> queryAll(Ad ad) {
		return adDAO.query(ad);
	}

	@Override
	public boolean update(Ad ad) {
		try {
			adDAO.updateByPrimaryKeySelective(ad);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			adDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
