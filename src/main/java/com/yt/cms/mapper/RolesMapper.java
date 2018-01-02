package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Roles;

public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    List<Roles> query(Roles record);
    
    int deleteLogicById(Integer id);
}