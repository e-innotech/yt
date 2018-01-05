package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
import com.yt.cms.model.UserGroup;
import com.yt.cms.service.UserGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userGroup")
@Api(value = "用户组服务",description = "提供RESTful风格API的用户组的增删改查服务")
public class UserGroupController {
	@Autowired
	private UserGroupService userGroupService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询用户组列表")
	public AjaxResponseBody query(@RequestParam(required=false) String groupName,
			@RequestParam Integer pageNum, @RequestParam Integer pageSize){
		long total = userGroupService.queryCount(groupName);
		Page page = new Page(pageNum,pageSize);
		List<UserGroup> list = userGroupService.query(groupName, page);
		PageInfo<UserGroup> pageInfo = new PageInfo<UserGroup>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
/*	*//**
	 * 树形展现
	 * @return
	 *//*
	@GetMapping("/queryAll")
	@ApiOperation("查询用户组列表")
	public List<UserGroup> queryAll(){
		return userGroupService.queryAll();
	}*/
	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询用户组")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		UserGroup result = userGroupService.findById(id);
		return new AjaxResponseBody(true, Const.SUCCESS,result);
	}
	/**
	 * 新增用户组
	 * @param userGroup
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加用户组")
	public AjaxResponseBody add(@RequestBody UserGroup userGroup) {
		boolean created = userGroupService.save(userGroup);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 修改用户组
	 * @param userGroup
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation("修改用户组")
	public AjaxResponseBody update(@RequestBody UserGroup userGroup){
		boolean created = userGroupService.update(userGroup);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 删除用户组
	 * @param userGroup
	 * @return
	 */
	@GetMapping("/delete")
	@ApiOperation("删除用户组")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = userGroupService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
}
