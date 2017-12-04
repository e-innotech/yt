package com.yt.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.mapper.UserMapper;

@RestController
@EnableAutoConfiguration
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/hello/{myName}")
	String index(@PathVariable String myName) {
		return "Hello " + myName + "!!!";
	}
	@RequestMapping("/user/queryAll")
	String queryAll() {
		userMapper.getAll();
		return "userList";
	}
}
