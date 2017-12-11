package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.RolesResource;

public interface RolesResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolesResource record);

    int insertSelective(RolesResource record);

    RolesResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolesResource record);

    int updateByPrimaryKey(RolesResource record);
    
    List<RolesResource> query();
}