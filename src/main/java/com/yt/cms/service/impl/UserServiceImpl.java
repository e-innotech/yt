package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yt.cms.common.Page;
import com.yt.cms.mapper.UserMapper;
import com.yt.cms.model.User;
import com.yt.cms.model.UserInfoUpdate;
import com.yt.cms.model.UserUpdatePwd;
import com.yt.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userDAO;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
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
	public List<User> query(String userName, Page page) {
		return userDAO.query(userName, page);
	}

	@Override
	public long queryCount(String userName) {
		return userDAO.queryCount(userName);
	}

	@Override
	public boolean update(UserInfoUpdate user) {
		try {
			int row = userDAO.updateByPrimaryKeySelective(user);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePwd(UserUpdatePwd user) {
		try {
			int row = userDAO.updatePwd(user);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

/*	@Override
	public boolean setUserGroup4User(int userId, int userGroupId) {
		try {
			int row = userDAO.setUserGroup4User(userId,userGroupId);
			if(row == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}*/

	
}
