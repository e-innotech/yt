package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.Members;

public interface MembersMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Members record);

    Members selectByPrimaryKey(Integer id);

    int selectByUname(String uname);
    
    int updateByPrimaryKeySelective(Members record);

    List<Members> query(@Param("record") Members record, @Param("page") Page page);
    
    long queryCount(Members record);
    
}