package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.Ad;
import com.yt.cms.model.AdOffLine;

public interface AdMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ad record);
    
    int offLine(AdOffLine line);

    List<Ad> query(@Param("ad") Ad ad, @Param("page") Page page);
    
    long queryCount(Ad ad);
}