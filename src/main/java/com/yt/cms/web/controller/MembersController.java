package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.yt.cms.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/members")
@Api(value = "会员服务")
public class MembersController {
	@Autowired
	private MemberService memberService;

	/**
	 * 会员注册
	 * @param Ad
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加会员")
	public AjaxResponseBody add(@RequestBody Members members) {
		boolean created = memberService.save(members);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询会员")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		Members result = memberService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	
	/**
	 * 按照会员注册名查询
	 * @param id
	 * @return
	 */
	@GetMapping("/find/name")
	@ApiOperation("按照注册名查询会员")
	public AjaxResponseBody findByUName(@RequestParam String uname) {
		boolean result = memberService.findByUname(uname);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	
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
	 * 修改会员信息
	 * @param Ad
	 * @return
	 */
	@PostMapping("/update/info")
	@ApiOperation("修改会员信息")
	public AjaxResponseBody update(@RequestBody MemberInfos info){
		boolean created = memberService.updateInfo(info);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
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
