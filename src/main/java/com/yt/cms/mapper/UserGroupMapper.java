package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.UserGroup;

public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
    
    List<UserGroup> query();
}