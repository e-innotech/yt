package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.AdPositions;

/**
 * 广告位接口定义
 * @author Admin
 *
 */
public interface AdPositionsService {
	/**
	 * 新增广告位
	 * @param adPositions
	 * @return
	 */
	public boolean save(AdPositions adPositions);
	
	/**
	 * 按照id查询广告位
	 * @param id
	 * @return
	 */
	public AdPositions findById(Integer id);
	/**
	 * 按照AdPositions 参数查询
	 * 列表显示广告位信息
	 * @return
	 */
	public List<AdPositions> queryAll(AdPositions adPositions);
	/**
	 * 更新广告位信息
	 * @param adPositions
	 * @return
	 */
	public boolean update(AdPositions adPositions);
	/**
	 * 按照广告位id删除广告位
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	
}
