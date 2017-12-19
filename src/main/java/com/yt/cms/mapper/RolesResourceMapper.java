package com.yt.cms.mapper;

import com.yt.cms.model.RolesResource;

public interface RolesResourceMapper {

    int insertSelective(RolesResource record);

    int updateByPrimaryKeySelective(RolesResource record);
    
    int deleteByRolesId(Integer rolesId);
}