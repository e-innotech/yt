package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.RolesResource;

public interface RolesResourceMapper {

    int insertSelective(RolesResource record);

    int updateByPrimaryKeySelective(RolesResource record);
    
    int deleteByRolesId(Integer rolesId);
    
    int insertBatch(List<RolesResource> record);

    /**
     * 批量删除指定角色id下的资源id
     * @param rolesId
     * @param resourceIds
     */
    void deleteByRolesIdResourceIds(@Param("rolesId") Integer rolesId, @Param("resourceIds") List<Integer> resourceIds);
    
}