package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.MembersCommentsNewsMapper;
import com.yt.cms.model.MembersCommentsNews;
import com.yt.cms.service.MemberCommentsNewsService;
@Service
public class MemberCommentsNewsServiceImpl implements MemberCommentsNewsService {
	@Autowired
	private MembersCommentsNewsMapper commentDAO;
	
	@Override
	public boolean save(MembersCommentsNews comment) {
		commentDAO.insertSelective(comment);
		if(comment.getId()  > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<MembersCommentsNews> queryAll(MembersCommentsNews comment) {
		return commentDAO.query(comment);
	}

	@Override
	public boolean delete(MembersCommentsNews comment) {
		try {
			commentDAO.deleteByModel(comment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
