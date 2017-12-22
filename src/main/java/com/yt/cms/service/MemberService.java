package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.MemberInfos;
import com.yt.cms.model.Members;
/**
 * 会员服务接口
 * @author admin
 *
 */
public interface MemberService {

	/**
	 * 会员注册，只开放前端注册
	 * @param member
	 * @return
	 */
	public boolean save(Members member);
	
	/**
	 * 按照id查询会员信息
	 * @param id
	 * @return
	 */
	public MemberInfos findById(Integer id);
	
	/**
	 * 按照注册用户名查询是否已经注册
	 * @param uname
	 * @return true 已注册
	 */
	public boolean findByUname(String uname);
	/**
	 * 按照member 参数查询
	 * 列表显示会员信息
	 * @param member
	 * @return
	 */
	public List<Members> queryAll(Members member);
	/**
	 * 更新会员信息
	 * @param memberInfo
	 * @return
	 */
	public boolean updateInfo(MemberInfos memberInfo);
	
	/**
	 * 修改会员密码
	 * @param members
	 * @return
	 */
	public boolean update(Members members);
	
	
}
