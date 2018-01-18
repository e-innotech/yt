package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.model.Ad;
import com.yt.cms.model.AdOffLine;

/**
 * 广告接口定义
 * @author admin
 *
 */
public interface AdService {
	/**
	 * 新增广告
	 * @param Ad
	 * @return
	 */
	public boolean save(Ad ad);
	
	/**
	 * 按照id查询广告
	 * @param id
	 * @return
	 */
	public Ad findById(Integer id);
	/**
	 * 按照Ad 参数查询
	 * 列表显示广告信息
	 * @param ad
	 * @return
	 */
	public List<Ad> queryAll(Ad ad, Page page);
	/**
	 * 查询count
	 * @param ad
	 * @return
	 */
	public long queryCount(Ad ad);
	/**
	 * 更新广告信息
	 * @param Ad
	 * @return
	 */
	public boolean update(Ad ad);
	
	/**
	 * 更新广告信息
	 * @param line
	 * @return
	 */
	public boolean offLine(AdOffLine line);
	/**
	 * 按照广告id删除广告
	 * @param id
	 * @return
	 */
	public boolean deleteLogicById(Integer id);
	
	
}
