package com.yt.cms.front.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.front.model.MembersComments;
/**
 * 会员评论服务接口
 * 会员填写评论、会员查看自己的评论
 * @author admin
 *
 */
public interface MemberCommentsService {

	/**
	 * 会员填写评论
	 * @param comment
	 * @return
	 */
	public boolean save(MembersComments comment);
	
	/**
	 * 按照comment 参数查询
	 * 列表显示会员评论信息
	 * @param comment
	 * @return
	 */
	public List<MembersComments> queryAll(MembersComments comment,Page page);
	
	/**
	 * 按照comment 参数查询
	 * 列表显示会员评论信息
	 * @param comment
	 * @return
	 */
	public long queryCount(MembersComments comment);
	
}
