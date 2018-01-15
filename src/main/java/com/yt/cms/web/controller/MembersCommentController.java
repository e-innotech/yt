package com.yt.cms.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	public AjaxResponseBody add(@RequestBody MembersCommentsNews comment) {
		if(comment.getMembersId() == null || comment.getContent() == null || comment.getWebsiteId() == null ) {
			return new AjaxResponseBody(false,Const.FAILED,"会员没有登陆，不能评论");
		}
		boolean created = memberCommentService.save(comment);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询会员评论列表")
	public AjaxResponseBody query(@RequestParam(required=false) String content, // 评论内容
			@RequestParam(required=false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startDate, // 评论日期
			@RequestParam(required=false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate, // 评论日期
			@RequestParam(required=false) String newsTitle, // 评论的新闻标题
			@RequestParam(required=false) String memberUName, //评论用户名
			@RequestParam(required=false) String siteName, //评论网站名称
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		MembersCommentsNews comment = new MembersCommentsNews();
		comment.setContent(content);
		comment.setStartDate(startDate);
		comment.setEndDate(endDate);
		comment.setSiteName(siteName);
		comment.setNewsTitle(newsTitle);
		comment.setMemberUName(memberUName);
		
		Page page = new Page(pageNum,pageSize);
		long total = memberCommentService.queryCount(comment);
		List<MembersCommentsNews> list =  memberCommentService.queryAll(comment,page);
		PageInfo<MembersCommentsNews> pageInfo = new PageInfo<MembersCommentsNews>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	
	
	/**
	 * 前端会员自己的评论列表页面
	 * 网站id和会员id必传
	 * @return
	 */
	@GetMapping("/personal/query")
	@ApiOperation("查询会员自己的评论列表")
	public AjaxResponseBody queryByMemberId(@RequestParam(required=false) String content, // 评论内容
			@RequestParam(required=false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startDate, // 评论日期
			@RequestParam(required=false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate, // 评论日期
			@RequestParam(required=false) String newsTitle, // 评论的新闻标题
			@RequestParam(required=true) Integer membersId, //评论用户
			@RequestParam(required=true) String siteName, //评论网站名称
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		MembersCommentsNews comment = new MembersCommentsNews();
		comment.setContent(content);
		comment.setStartDate(startDate);
		comment.setEndDate(endDate);
		comment.setSiteName(siteName);
		comment.setNewsTitle(newsTitle);
		comment.setMembersId(membersId);
		
		Page page = new Page(pageNum,pageSize);
		long total = memberCommentService.queryCount(comment);
		List<MembersCommentsNews> list =  memberCommentService.queryAll(comment,page);
		PageInfo<MembersCommentsNews> pageInfo = new PageInfo<MembersCommentsNews>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	/**
	 * 管理员删除评论
	 * @param comment
	 * @return
	 */
	@GetMapping("/delete")
	@ApiOperation("管理员删除评论")
	@LogAnnotation(action="删除会员评论")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean result = memberCommentService.deleteLogical(id);
		if(!result) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}

	
}
