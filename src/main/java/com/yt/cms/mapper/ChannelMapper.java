package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.Channel;

public interface ChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);
    
    List<Channel> query(@Param("record") Channel record, @Param("page") Page page);
    
    long queryCount(Channel record);
    /**
     * 前端输入栏目名称，后端要保证名称在数据库的唯一性
     * @param channelName
     * @return
     */
    int findChannelName(String channelName);
    
    List<Channel> queryByIds(@Param("ids") List<Integer> ids);
}