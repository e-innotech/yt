package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.MembersCommentsNews;

public interface MembersCommentsNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MembersCommentsNews record);

    List<MembersCommentsNews> query(MembersCommentsNews record);
    
    int deleteLogical(Integer id);

}