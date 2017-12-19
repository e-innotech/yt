package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);
    
    List<Resource> query(Resource record);
    
    int setResourceModule(@Param("id") Integer id, @Param("moduleId") Integer moduleId);
}