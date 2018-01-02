package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);
    
    List<Resource> query(Resource record);
    
    int deleteLogicById(Integer id);
}