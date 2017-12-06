package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.User;

public interface UserMapper {
	
	List<User> getAll();
	
	User findById(Integer id);

	void insert(User user);

	void update(User user);

	void disableOrEnable(User user);
	
	void delete(Long id);

}