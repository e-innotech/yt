package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yt.cms.mapper.UserMapper;
import com.yt.cms.model.User;
import com.yt.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userDAO;
	
	@Override
	public boolean save(User user) {
		userDAO.insert(user);
        if (user.getId() > 0) {
            return true;
        }
		return false;
	}

	@Override
	public User login(User user) {
		// TODO 密码加密与解密
		User user_db = userDAO.login(user);
		return user_db;
	}


	@Override
	public User findById(Integer id) {
		return userDAO.findById(id);
	}

	@Override
	public boolean findByUserName(String userName) {
		int user_db = userDAO.findByUserName(userName);
		if(user_db > 0) {
			return true;
		}
		return false;
	}


/*	@Override
	public PageInfo query(User user) {
		Integer count = userDAO.queryCount(user);
		List<User> list = new ArrayList<User>();
		if(count != 0) {
			list = userDAO.query(user);
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setList(list);
		pageInfo.setTotalSize(count);
		return pageInfo;
	}*/

	
	@Override
	public List<User> query(String userName, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return userDAO.query(userName);
	}

	@Override
	public boolean update(User user) {
		try {
			userDAO.update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean disableOrEnable(User user) {
		try {
			if(user.getIsUse() == 0) {
				user.setIsUse(1);
			} else if(user.getIsUse() == 1) {
				user.setIsUse(0);
			} else {
				throw new Exception();
			}
			userDAO.disableOrEnable(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setUserGroup4User(int userId, int userGroupId) {
		try {
			userDAO.setUserGroup4User(userId,userGroupId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
