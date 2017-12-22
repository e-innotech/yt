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

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
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
	public HttpEntity<?> add(@RequestBody Members members) {
		boolean created = memberService.save(members);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/{id}")
	@ApiOperation("按照id查询会员")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		MemberInfos result = memberService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<MemberInfos>(result, status);
	}
	
	/**
	 * 按照会员注册名查询
	 * @param id
	 * @return
	 */
	@GetMapping("/find/{uname}")
	@ApiOperation("按照id查询会员")
	public HttpEntity<?> findById(@PathVariable String uname) {
		boolean result = memberService.findByUname(uname);
		HttpStatus status = result  ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Boolean>(result, status);
	}
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询会员列表")
	public List<Members> query(){
		Members member = new Members();
		return memberService.queryAll(member);
	}

	/**
	 * 修改会员
	 * @param Ad
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改会员")
	public HttpEntity<?> update(@RequestBody MemberInfos info){
		boolean created = memberService.updateInfo(info);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改会员密码
	 * @param Ad
	 * @return
	 */
	@PutMapping("/update/p")
	@ApiOperation("修改会员")
	public HttpEntity<?> updateP(@RequestBody Members member){
		boolean created = memberService.update(member);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	

	
}
