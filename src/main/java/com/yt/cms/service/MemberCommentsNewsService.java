package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.MembersCommentsNews;
/**
 * 会员评论服务接口
 * 会员填写评论、后台管理员查看所有评论、后台管理员删除评论
 * @author admin
 *
 */
public interface MemberCommentsNewsService {

	/**
	 * 会员填写评论
	 * @param comment
	 * @return
	 */
	public boolean save(MembersCommentsNews comment);
	
	/**
	 * 按照comment 参数查询
	 * 列表显示会员评论信息
	 * @param comment
	 * @return
	 */
	public List<MembersCommentsNews> queryAll(MembersCommentsNews comment);
	
	/**
	 * 删除评论
	 * @param comment
	 * @return
	 */
	public boolean delete(MembersCommentsNews comment);
	
	
}
