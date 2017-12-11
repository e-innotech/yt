package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.UserGroupRoles;

public interface UserGroupRolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupRoles record);

    int insertSelective(UserGroupRoles record);

    UserGroupRoles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroupRoles record);

    int updateByPrimaryKey(UserGroupRoles record);
    
    List<UserGroupRoles> query();
}