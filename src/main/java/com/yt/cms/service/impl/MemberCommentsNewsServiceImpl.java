package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.Page;
import com.yt.cms.mapper.MembersCommentsNewsMapper;
import com.yt.cms.model.MembersCommentsNews;
import com.yt.cms.service.MemberCommentsNewsService;
@Service
public class MemberCommentsNewsServiceImpl implements MemberCommentsNewsService {
	@Autowired
	private MembersCommentsNewsMapper commentDAO;


	@Override
	public List<MembersCommentsNews> queryAll(MembersCommentsNews comment,Page page) {
		return commentDAO.query(comment,page);
	}
	

	@Override
	public long queryCount(MembersCommentsNews comment) {
		return commentDAO.queryCount(comment);
	}

	@Override
	public boolean deleteLogical(Integer id) {
		try {
			int row = commentDAO.deleteLogical(id);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
