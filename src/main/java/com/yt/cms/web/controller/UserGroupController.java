package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.Const;
import com.yt.cms.common.AjaxResponseBody;
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
	@GetMapping
	@ApiOperation("查询用户组列表")
	public List<UserGroup> query(){
		return userGroupService.query();
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation("按照id查询用户组")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		UserGroup result = userGroupService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<UserGroup>(result, status);
	}
	/**
	 * 新增用户组
	 * @param userGroup
	 * @return
	 */
	@PostMapping
	@ApiOperation("添加用户组")
	public HttpEntity<?> add(@RequestBody UserGroup userGroup) {
		boolean created = userGroupService.save(userGroup);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改用户组
	 * @param userGroup
	 * @return
	 */
	@PutMapping
	@ApiOperation("修改用户组")
	public HttpEntity<?> update(@RequestBody UserGroup userGroup){
		boolean created = userGroupService.update(userGroup);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	
}
