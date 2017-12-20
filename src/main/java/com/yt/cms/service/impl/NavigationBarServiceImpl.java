package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.NavigationBarMapper;
import com.yt.cms.model.NavigationBar;
import com.yt.cms.service.NavigationBarService;
@Service
public class NavigationBarServiceImpl implements NavigationBarService {
	@Autowired
	private NavigationBarMapper navigationBarDAO;
	@Override
	public boolean save(NavigationBar bar) {
		navigationBarDAO.insertSelective(bar);
		if(bar.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public NavigationBar findById(Integer id) {
		return navigationBarDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<NavigationBar> queryAll(NavigationBar bar) {
		return navigationBarDAO.query(bar);
	}

	@Override
	public boolean update(NavigationBar bar) {
		try {
			navigationBarDAO.updateByPrimaryKeySelective(bar);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			navigationBarDAO.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
