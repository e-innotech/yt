package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.Page;
import com.yt.cms.mapper.UserLogsMapper;
import com.yt.cms.model.UserLogs;
import com.yt.cms.service.UserLogsService;
@Service
public class UserLogsServiceImpl implements UserLogsService {
	@Autowired
	private UserLogsMapper logDAO;
	@Override
	public boolean save(UserLogs log) {
		logDAO.insertSelective(log);
		if(log.getId() > 0) {
			return true;
		}
		return false;
	}


	@Override
	public List<UserLogs> queryAll(UserLogs log, Page page) {
		return logDAO.query(log,page);
	}


	@Override
	public long queryCount(UserLogs log) {
		return logDAO.queryCount(log);
	}


}
