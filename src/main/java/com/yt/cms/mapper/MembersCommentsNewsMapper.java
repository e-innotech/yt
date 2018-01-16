package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.MembersCommentsNews;

public interface MembersCommentsNewsMapper {
    int deleteByPrimaryKey(Integer id);

    List<MembersCommentsNews> query(@Param("record") MembersCommentsNews record, @Param("page") Page page);
    
    long queryCount(MembersCommentsNews record);
    
    int deleteLogical(Integer id);

}