package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.WebsitesMapper;
import com.yt.cms.model.Websites;
import com.yt.cms.service.WebsitesService;
@Service
public class WebsitesServiceImpl implements WebsitesService {
	@Autowired
	private WebsitesMapper websitesDAO;
	@Override
	public boolean save(Websites web) {
		websitesDAO.insertSelective(web);
		if(web.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Websites findById(Integer id) {
		return websitesDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<Websites> queryAll(Websites web, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return websitesDAO.query(web);
	}

	@Override
	public boolean update(Websites web) {
		try {
			int row = websitesDAO.updateByPrimaryKeySelective(web);
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
			int row = websitesDAO.deleteLogicById(id);
			if(row == 1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
