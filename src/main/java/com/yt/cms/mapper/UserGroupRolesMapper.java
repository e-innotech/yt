package com.yt.cms.mapper;

import com.yt.cms.model.UserGroupRoles;

public interface UserGroupRolesMapper {

    int insertSelective(UserGroupRoles record);

    int updateByPrimaryKeySelective(UserGroupRoles record);

    int deleteByUserGroupId(Integer userGroupId);
}