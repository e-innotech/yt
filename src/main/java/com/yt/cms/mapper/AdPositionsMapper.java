package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.common.Page;
import com.yt.cms.model.AdPositions;

public interface AdPositionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(AdPositions record);
    /**
     * 级联查询广告位下的所有广告
     * @param id
     * @return
     */
    AdPositions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdPositions record);

    List<AdPositions> query(@Param("record") AdPositions record, @Param("page") Page page);
    
    long queryCount(AdPositions record);
    
}