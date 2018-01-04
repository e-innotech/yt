package com.yt.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
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
	public List<Ad> queryAll(Ad ad, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return adDAO.query(ad);
	}

	@Override
	public boolean update(Ad ad) {
		try {
			int row = adDAO.updateByPrimaryKeySelective(ad);
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
			Ad record = new Ad();
			record.setId(id);
			record.setIsDel(Const.DELETE_FLAG);
			record.setDelDate(new Date());
			int row = adDAO.updateByPrimaryKeySelective(record);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
