package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.WebsitesNavigationBar;

/**
 * 
 * 网站栏位关系接口定义
 * @author admin
 *
 */
public interface WebsitesNavigationBarService {
	/**
	 * 批量insert
	 * 设置网站的栏位
	 * @param moduleList
	 * @return
	 */
	public boolean save(List<WebsitesNavigationBar> moduleList);

	/**
	 * 批量更新
	 * 更新网站栏位数据
	 * 网站与栏位表关系的更新，可以理解成旧关系的解除与新关系的建立
	 * 前段传递之前的网站配置的栏目id值，更新之后的栏目id值
	 * 如  网站1 关联的栏目是 A、B、C 修改成 A、B、D、E 则后台insert D|E， delete C
	 * 前端传递2个id数组列表
	 * @param websitesId
	 * @param old_navigationBarIds
	 * @param new_navigationBarIds
	 * @return
	 */
	public boolean update(Integer websitesId, Integer[] old_navigationBarIds, Integer[] new_navigationBarIds);

}
