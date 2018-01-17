package com.yt.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.annotations.LogAnnotation;
import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
import com.yt.cms.model.MemberInfos;
import com.yt.cms.model.Members;
import com.yt.cms.model.UpdatePwd;
import com.yt.cms.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/members")
@Api(value = "会员服务")
public class MembersController {
	@Autowired
	private MemberService memberService;

	/***********************前端接口***************************************/
	 
	
	/**
	 * 会员注册
	 * @param Ad
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("会员注册")
	public AjaxResponseBody add(@Valid @RequestBody Members members,BindingResult result) {
		// 检查当前会员名是否已经注册
		boolean is_exist = memberService.findByUname(members.getUname());
		AjaxResponseBody response = new AjaxResponseBody();
		if(is_exist) {
			response.setMsg("该名称已被注册，请使用其他名称！！！");
			response.setSuccess(false);
			return response;
		}		
		boolean created = memberService.save(members);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 按照id查询
	 * 个人资料页面接口
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询会员以及会员信息")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		Members result = memberService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	
//	/**
//	 * 按照会员注册名查询
//	 * @param id
//	 * @return
//	 */
//	@GetMapping("/find/name")
//	@ApiOperation("按照注册名查询会员")
//	public AjaxResponseBody findByUName(@RequestParam String uname) {
//		boolean result = memberService.findByUname(uname);
//		return new AjaxResponseBody(true,Const.SUCCESS,result);
//	}
	
	
	/**
	 * 修改会员信息
	 * @param Ad
	 * @return
	 */
	@PostMapping("/update/info")
	@ApiOperation("修改会员信息")
	public AjaxResponseBody update(@Valid @RequestBody MemberInfos info,BindingResult result,HttpServletRequest request){
		HttpSession session = request.getSession();
		Members members_session = (Members) session.getAttribute(Const.SESSION_MEMBERS_KEY);
		
		if(members_session == null || members_session.getId() == null) {
			return new AjaxResponseBody(false,Const.SESSION_TIMEOUT,Const.SESSION_TIMEOUT_ERROR_CODE);
		}
		info.setMemberId(members_session.getId());
		boolean created = memberService.updateInfo(info);
		Members new_members = memberService.findById(info.getMemberId());
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,new_members);
	}
	
	/**
	 * TODO isuse 不可用的会员是否还可以登陆
	 * 会员登录
	 * @param member
	 */
	@PostMapping("/login")
	@ApiOperation("会员登陆")
	public AjaxResponseBody login(@Valid @RequestBody Members member,BindingResult result, HttpServletRequest request) {
		Members db_member = memberService.login(member);
		HttpSession session = request.getSession();
		AjaxResponseBody response = new AjaxResponseBody();
		if(db_member == null) {
			response.setMsg(Const.LOGIN_FAILED);
			response.setSuccess(false);
		} else {
			session.setAttribute(Const.SESSION_MEMBERS_KEY, db_member);
			response.setMsg(Const.LOGIN_SUCCESS);
			response.setSuccess(true);
			response.setData(db_member);
		}
		return response;
	}
	/**
	 * 会员退出
	 */
	@GetMapping("/logout")
	@ApiOperation("会员退出")
	public AjaxResponseBody logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(Const.SESSION_MEMBERS_KEY);
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.LOGOUT_SUCCESS);
		response.setSuccess(true);
		return response;
	}
	
	/**
	 * 还需要验证新旧密码是否相同
	 * @param user
	 * @return
	 */
	@PostMapping("/pwd")
	@ApiOperation("更新会员密码")
	public AjaxResponseBody updatePwd(@Valid @RequestBody UpdatePwd user,  HttpServletRequest request,BindingResult result) {
		HttpSession session = request.getSession();
		Members members_session = (Members) session.getAttribute(Const.SESSION_MEMBERS_KEY);
		AjaxResponseBody response = new AjaxResponseBody();
		if(members_session == null || members_session.getId() == null) {
			response.setMsg(Const.SESSION_TIMEOUT);
			response.setSuccess(false);
			response.setErrCode(Const.SESSION_TIMEOUT_ERROR_CODE);
			return response;
		}
		if(StringUtils.isEmpty(user.getPassWord()) || StringUtils.isEmpty(user.getCurrentPwd())) {
			response.setMsg(Const.PASSWORD_REQUIRED);
			response.setSuccess(false);
			return response;
		}
		user.setId(members_session.getId());
		
		boolean flag = memberService.updatePwd(user);
		if(!flag) {
			response.setMsg(Const.FAILED);
			response.setSuccess(false);
		} else {
			response.setMsg(Const.SUCCESS);
			response.setSuccess(true);
		}
		return response;
	}
	
	/***********************后台接口***************************************/
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询会员列表")
	public AjaxResponseBody query(@RequestParam(required=false) String uname,
			@RequestParam(required=false) Integer isUse,
			@RequestParam(required=false) Integer isGag,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		Members member = new Members();
		member.setIsGag(isGag);
		member.setIsUse(isUse);
		member.setUname(uname);
		Page page = new Page(pageNum,pageSize);
		long total = memberService.queryCount(member);
		List<Members> list = memberService.queryAll(member,page);
		PageInfo<Members> pageInfo = new PageInfo<Members>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	
	/**
	 * 启用、停用、禁言、取消禁言
	 * @param
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation("启用、停用、禁言、取消禁言")
	@LogAnnotation(action="修改会员[启用、停用、禁言、取消禁言]状态")
	public AjaxResponseBody update(@RequestBody Members member){
		boolean created = memberService.update(member);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	
	
	
}
