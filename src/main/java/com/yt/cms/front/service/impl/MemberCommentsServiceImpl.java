package com.yt.cms.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.common.Page;
import com.yt.cms.front.mapper.MembersCommentsMapper;
import com.yt.cms.front.model.MembersComments;
import com.yt.cms.front.service.MemberCommentsService;
@Service
public class MemberCommentsServiceImpl implements MemberCommentsService {
	@Autowired
	private MembersCommentsMapper commentDAO;
	
	@Override
	public boolean save(MembersComments comment) {
		commentDAO.insertSelective(comment);
		if(comment.getId()  > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<MembersComments> queryAll(MembersComments comment,Page page) {
		return commentDAO.query(comment,page);
	}
	

	@Override
	public long queryCount(MembersComments comment) {
		return commentDAO.queryCount(comment);
	}


}
