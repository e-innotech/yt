package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.MembersCollectNewsMapper;
import com.yt.cms.model.MembersCollectNews;
import com.yt.cms.service.MemberCollectNewsService;
@Service
public class MemberCollectNewsServiceImpl implements MemberCollectNewsService {
	@Autowired
	private MembersCollectNewsMapper collectDAO;
	@Override
	public boolean save(MembersCollectNews collect) {
		collectDAO.insertSelective(collect);
		if(collect.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<MembersCollectNews> queryAll(MembersCollectNews collect) {
		return collectDAO.query(collect);
	}

	@Override
	public boolean delete(Integer collectId) {
		try {
			collectDAO.deleteByPrimaryKey(collectId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
