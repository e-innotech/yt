package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.model.MembersCollectNews;
/**
 * 会员收藏服务接口
 * 会员收藏，取消收藏（删除），查看自己的所有收藏
 * @author admin
 *
 */
public interface MemberCollectNewsService {

	/**
	 * 会员收藏新闻
	 * @param collect
	 * @return
	 */
	public boolean save(MembersCollectNews collect);
	
	/**
	 * 按照collect 参数查询
	 * 列表显示会员收藏信息
	 * @param collect
	 * @return
	 */
	public List<MembersCollectNews> queryAll(MembersCollectNews collect,Page page);
	
	/**
	 * 按照collect 参数查询
	 * @param collect
	 * @return
	 */
	public long queryCount(MembersCollectNews collect);
	
	/**
	 * 取消收藏
	 * @param collectId
	 * @return
	 */
	public boolean cancelCollect(Integer collectId);
	
	
}
