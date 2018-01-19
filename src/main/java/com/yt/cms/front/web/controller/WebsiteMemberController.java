package com.yt.cms.front.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
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
import com.yt.cms.front.model.MembersComments;
import com.yt.cms.front.service.MemberCommentsService;
import com.yt.cms.model.Members;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 网站会员请求
 * @author admin
 *
 */
@RestController
@RequestMapping("/web/member/comment")
@Api(value = "网站会员相关接口",description = "网站会员相关服务")
public class WebsiteMemberController {
	@Autowired
	private MemberCommentsService memberCommentsService;
	
	/**
	 * 会员写评论
	 * @param comment
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("会员写评论")
	public AjaxResponseBody add(@RequestBody MembersComments comment,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Members session_member = (Members)session.getAttribute(Const.SESSION_MEMBERS_KEY);
	
		if(session_member == null || session_member.getId() == null ) {
			return  new AjaxResponseBody(false,Const.SESSION_TIMEOUT,null);
		}
		if(comment.getContent() == null ) {
			return new AjaxResponseBody(false,Const.FAILED,"评论内容不能为空");
		}
		if(comment.getPublishId() == null ) {
			return new AjaxResponseBody(false,Const.FAILED,"评论文章不存在，评论失败");
		}
		comment.setMembersId(session_member.getId());
		boolean created = memberCommentsService.save(comment);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 前端会员自己的评论列表页面
	 * 网站id和会员id必传
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询会员自己的评论列表")
	public AjaxResponseBody queryByMemberId(
			@NotNull(message="只有登陆之后才可以查看自己的评论内容") @RequestParam(required=true) Integer membersId, //评论用户
			@NotBlank(message="评论的网站名不能为空") @RequestParam(required=true) Integer websiteId, //评论网站名称
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		
		MembersComments comment = new MembersComments();
		
		comment.setWebsiteId(websiteId);
		comment.setMembersId(membersId);
		
		Page page = new Page(pageNum,pageSize);
		long total = memberCommentsService.queryCount(comment);
		List<MembersComments> list =  memberCommentsService.queryAll(comment,page);
		PageInfo<MembersComments> pageInfo = new PageInfo<MembersComments>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	
}
