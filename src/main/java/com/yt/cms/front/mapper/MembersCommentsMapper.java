package com.yt.cms.front.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.MembersComments;

public interface MembersCommentsMapper {

    int insertSelective(MembersComments record);

    List<MembersComments> query(@Param("record") MembersComments record, @Param("page") Page page);
    
    long queryCount(MembersComments record);
    

}