package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.model.MembersCommentsNews;
import com.yt.cms.service.MemberCommentsNewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member/comment")
@Api(value = "会员评论服务")
public class MembersCommentController {
	@Autowired
	private MemberCommentsNewsService memberCommentService;

	/**
	 * 会员写评论
	 * @param comment
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("会员写评论")
	public HttpEntity<?> add(@RequestBody MembersCommentsNews comment) {
		boolean created = memberCommentService.save(comment);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询会员评论列表")
	public List<MembersCommentsNews> query(){
		MembersCommentsNews comment = new MembersCommentsNews();
		return memberCommentService.queryAll(comment);
	}

	/**
	 * 管理员删除评论
	 * @param comment
	 * @return
	 */
	@DeleteMapping("/delete")
	@ApiOperation("管理员删除评论")
	public HttpEntity<?> delete(@RequestBody MembersCommentsNews comment){
		boolean result = memberCommentService.delete(comment);
		if(!result) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
	}

	
}
