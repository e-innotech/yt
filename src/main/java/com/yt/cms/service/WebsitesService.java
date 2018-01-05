package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.model.Websites;

/**
 * 网站接口定义
 * @author admin
 *
 */
public interface WebsitesService {
	/**
	 * 新增网站
	 * @param web
	 * @return
	 */
	public boolean save(Websites web);
	
	/**
	 * 按照id查询网站
	 * @param id
	 * @return
	 */
	public Websites findById(Integer id);
	/**
	 * 按照Websites 参数查询
	 * 列表显示网站信息
	 * @return
	 */
	public List<Websites> queryAll(Websites web, Page page);
	
	/**
	 * 按照Websites 参数查询
	 * @return
	 */
	public long queryCount(Websites web);
	/**
	 * 更新网站信息
	 * @param Websites
	 * @return
	 */
	public boolean update(Websites web);
	/**
	 * 按照网站id删除网站
	 * @param id
	 * @return
	 */
	public boolean deleteLogicById(Integer id);
	
	/**
	 * 查询网站id下所有的栏位数据
	 * @param WebsitesId
	 * @return
	 */
//	public List<NavigationBar> queryNavigationBarByWebsitesId(Integer WebsitesId);
	
}
