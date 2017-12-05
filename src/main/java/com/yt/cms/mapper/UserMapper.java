package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.entity.UserEntity;

public interface UserMapper {
	
	List<UserEntity> getAll();
	
	UserEntity findById(Integer id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void disableOrEnable(UserEntity user);
	
	void delete(Long id);

}