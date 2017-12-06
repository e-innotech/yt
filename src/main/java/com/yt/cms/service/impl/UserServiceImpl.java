package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.mapper.UserMapper;
import com.yt.cms.model.User;
import com.yt.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userDAO;
	
	@Override
	public boolean saveOne(User user) {
		userDAO.insert(user);
        if (user.getId() > 0) {
            return true;
        }
		return false;
	}

	@Override
	public User findById(Integer id) {
		return userDAO.findById(id);
	}

	@Override
	public List<User> queryAll() {
		return userDAO.getAll();
	}

	@Override
	public boolean update(User user) {
		
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

}
