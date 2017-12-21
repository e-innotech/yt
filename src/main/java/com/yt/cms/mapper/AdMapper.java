package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Ad;

public interface AdMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ad record);

    List<Ad> query(Ad ad);
}