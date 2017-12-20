package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.NavigationBar;

/**
 * 栏位接口定义
 * @author admin
 *
 */
public interface NavigationBarService {
	/**
	 * 新增栏位
	 * @param bar
	 * @return
	 */
	public boolean save(NavigationBar bar);
	
	/**
	 * 按照id查询栏位
	 * @param id
	 * @return
	 */
	public NavigationBar findById(Integer id);
	/**
	 * 按照NavigationBar 参数查询
	 * 列表显示栏位信息
	 * @return
	 */
	public List<NavigationBar> queryAll(NavigationBar bar);
	/**
	 * 更新栏位信息
	 * @param NavigationBar
	 * @return
	 */
	public boolean update(NavigationBar bar);
	/**
	 * 按照栏位id删除栏位
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 查询栏位id被那些网站引用
	 * 在查询单个栏位时级联查询网站信息
	 * @param NavigationBarId
	 * @return
	 */
//	public List<Websites> queryWebsitesByNavigationBarId(Integer navigationBarId);
	
}
