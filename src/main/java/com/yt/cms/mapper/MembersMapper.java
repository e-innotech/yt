package com.yt.cms.mapper;

import java.util.List;

import com.yt.cms.model.Members;

public interface MembersMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Members record);

    Members selectByPrimaryKey(Integer id);

    int selectByUname(String uname);
    
    int updateByPrimaryKeySelective(Members record);

    List<Members> query(Members record);
    
    int updatePwd(Members record);
}