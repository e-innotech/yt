package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.model.Channel;

/**
 * 栏位接口定义
 * @author admin
 *
 */
public interface ChannelService {
	/**
	 * 新增栏位
	 * @param bar
	 * @return
	 */
	public boolean save(Channel bar);
	
	/**
	 * 按照id查询栏位
	 * @param id
	 * @return
	 */
	public Channel findById(Integer id);
	/**
	 * 按照Channel 参数查询
	 * 列表显示栏位信息
	 * @return
	 */
	public List<Channel> queryAll(Channel bar,Page page);
	/**
	 * 更新栏位信息
	 * @param Channel
	 * @return
	 */
	public boolean update(Channel bar);
	/**
	 * 按照栏位id删除栏位
	 * @param id
	 * @return
	 */
	public boolean deleteLogicById(Integer id);
	/**
	 * 按照栏目名称查询栏目
	 * @param channelName
	 * @return 如果找到返回 true，否则返回false
	 */
	public boolean findByChannelName(String channelName);
	/**
	 * 查询栏位id被那些网站引用
	 * 在查询单个栏位时级联查询网站信息
	 * @param NavigationBarId
	 * @return
	 */
//	public List<Websites> queryWebsitesByNavigationBarId(Integer navigationBarId);
	
}
