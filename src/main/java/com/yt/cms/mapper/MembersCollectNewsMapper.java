package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.MembersCollectNews;

public interface MembersCollectNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MembersCollectNews record);

    MembersCollectNews selectByPrimaryKey(Integer id);

    List<MembersCollectNews> query(@Param("record") MembersCollectNews record, @Param("page") Page page);

    long queryCount(MembersCollectNews record);
    
    int cancelCollect(Integer id);
}