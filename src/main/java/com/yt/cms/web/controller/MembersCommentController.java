package com.yt.cms.web.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.model.MembersCommentsNews;
import com.yt.cms.model.News;
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
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.CREATED);
	}
	
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询会员评论列表")
	public PageInfo<MembersCommentsNews> query(@RequestParam(required=false) String content, // 评论内容
			@RequestParam(required=false) Date startDate, // 评论日期
			@RequestParam(required=false) Date endDate, // 评论日期
			@RequestParam(required=false) String newsTitle, // 评论的新闻标题
			@RequestParam(required=false) String memberUName, //评论用户名
			Page page){
		MembersCommentsNews comment = new MembersCommentsNews();
		comment.setContent(content);
		comment.setStartDate(startDate);
		comment.setEndDate(endDate);
		
		News news = new News();
		news.setNewsTitle(newsTitle);
		comment.setNews(news);
		
		comment.setMemberUName(memberUName);
		
		List<MembersCommentsNews> list =  memberCommentService.queryAll(comment,page);
		return new PageInfo<MembersCommentsNews>(list);
	}

	/**
	 * 管理员删除评论
	 * @param comment
	 * @return
	 */
	@DeleteMapping("/delete")
	@ApiOperation("管理员删除评论")
	public HttpEntity<?> delete(@RequestParam Integer id){
		boolean result = memberCommentService.deleteLogical(id);
		if(!result) {
			return new ResponseEntity<String>(Const.DELETE_NO_FOUND,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(Const.SUCCESS,HttpStatus.OK);
	}

	
}
