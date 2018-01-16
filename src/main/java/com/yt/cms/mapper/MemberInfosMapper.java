package com.yt.cms.mapper;

import com.yt.cms.model.MemberInfos;

public interface MemberInfosMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MemberInfos record);

    MemberInfos selectByPrimaryKey(Integer id);

    int updateByMemberIdSelective(MemberInfos record);

}