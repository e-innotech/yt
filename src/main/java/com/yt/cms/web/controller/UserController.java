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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.Const;
import com.yt.cms.common.Response;
import com.yt.cms.model.User;
import com.yt.cms.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "用户服务",description = "提供RESTful风格API的用户的增删改查服务")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/hello/{myName}")
	String index(@PathVariable String myName) {
		return "Hello " + myName + "!!!";
	}

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping(value="query")
	@ApiOperation("查询用户列表")
	List<User> query(){
		return userService.query();
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "{id}")
	@ApiOperation("按照id查询用户")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		User result = userService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<User>(result, status);
	}
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@PostMapping
	@ApiOperation("添加用户")
	public HttpEntity<?> add(@PathVariable  String userName,@PathVariable  String passWord) {
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		boolean created = userService.save(user);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		Response response = new Response();
		response.setMessage(Const.SUCCESS);
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
	}
	
	/**
	 * 启停用户
	 * @param user
	 * @return
	 */
	@PutMapping(value="{id}/{isUse}")
	@ApiOperation("启停用户")
	public HttpEntity<?> enableOrDisable(@PathVariable Integer id,@PathVariable Integer isUse) {
		User user = new User();
		user.setId(id);
		user.setIsUse(isUse);
		boolean flag = userService.disableOrEnable(user);
		if(!flag) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		Response response = new Response();
		response.setMessage(Const.SUCCESS);
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
	}
	
}
