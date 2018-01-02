package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.common.Page;
import com.yt.cms.mapper.MemberInfosMapper;
import com.yt.cms.mapper.MembersMapper;
import com.yt.cms.model.MemberInfos;
import com.yt.cms.model.Members;
import com.yt.cms.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MembersMapper membersDAO;
	@Autowired
	private MemberInfosMapper memberInfoDAO;
	@Override
	public boolean save(Members member) {
		membersDAO.insertSelective(member);
		if(member.getId() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Members findById(Integer id) {
		return membersDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean findByUname(String uname) {
		int num = membersDAO.selectByUname(uname);
		if(num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Members> queryAll(Members member,Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return membersDAO.query(member);
	}

	@Override
	public boolean updateInfo(MemberInfos memberInfo) {
		try {
			memberInfoDAO.updateByPrimaryKeySelective(memberInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Members members) {
		try {
			int row = membersDAO.updateByPrimaryKeySelective(members);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePwd(Members members) {
		try {
			int row = membersDAO.updatePwd(members);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



}
