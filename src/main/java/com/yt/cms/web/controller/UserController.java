package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.Response;
import com.yt.cms.entity.UserEntity;
import com.yt.cms.service.UserService;

@RestController
@EnableAutoConfiguration
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello/{myName}")
	String index(@PathVariable String myName) {
		return "Hello " + myName + "!!!";
	}
/*	@RequestMapping("/user/queryAll")
	String queryAll() {
		userService.getAll();
		return "userList";
	}*/
	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping("/user/query")
	List<UserEntity> queryAll(){
		return userService.queryAll();
	}
	/**
	 * 按照id查询
	 * @param id
	 * @return
	 */
	@RequestMapping("user/findById/{id}")
	UserEntity findOne(@PathVariable("id") Integer id){
		return userService.findById(id);
	}
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@RequestMapping("user/saveOne")
	HttpEntity<?> saveOne(UserEntity user) {
		boolean created = userService.saveOne(user);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		}
		Response response = new Response();
		response.setStatus(200);
		response.setMessage("新增成功！");
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
//		return response;
	}
	
	/**
	 * 启停用户
	 * @param user
	 * @return
	 */
	@RequestMapping("user/")
	Response enableOrDisable(UserEntity user) {
		userService.disableOrEnable(user);
		Response response = new Response();
		response.setStatus(200);
		response.setMessage("新增成功！");
		return response;
	}
	
}
