package com.yt.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.cms.entity.UserEntity;
import com.yt.cms.mapper.UserMapper;
import com.yt.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userDAO;
	
	@Override
	public boolean saveOne(UserEntity user) {
		userDAO.insert(user);
        if (user.getId() > 0) {
            return true;
        }
		return false;
	}

	@Override
	public UserEntity findById(Integer id) {
		return userDAO.findById(id);
	}

	@Override
	public List<UserEntity> queryAll() {
		return userDAO.getAll();
	}

	@Override
	public boolean update(UserEntity user) {
		
		return false;
	}

	@Override
	public boolean disableOrEnable(UserEntity user) {
		try {
			userDAO.disableOrEnable(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
