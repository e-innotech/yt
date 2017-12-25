package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.UserGroupRoles;

public interface UserGroupRolesMapper {

    int insertSelective(UserGroupRoles record);

    int updateByPrimaryKeySelective(UserGroupRoles record);

    int deleteByUserGroupId(Integer userGroupId);
    
    int insertBatch(List<UserGroupRoles> record);

    /**
     * 批量删除指定用户组id下的角色id
     * @param userGroupId
     * @param rolesIds
     */
    void deleteByUserGroupIdRolesIds(@Param("userGroupId") Integer userGroupId, @Param("rolesIds") List<Integer> rolesIds);
    
}