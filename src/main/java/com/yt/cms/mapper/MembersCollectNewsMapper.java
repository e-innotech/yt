package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.MembersCollectNews;

public interface MembersCollectNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MembersCollectNews record);

    MembersCollectNews selectByPrimaryKey(Integer id);

    List<MembersCollectNews> query(MembersCollectNews record);
    
}