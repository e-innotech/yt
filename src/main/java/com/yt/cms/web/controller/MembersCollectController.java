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
import com.yt.cms.model.MembersCollectNews;
import com.yt.cms.service.MemberCollectNewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member/collect")
@Api(value = "会员收藏服务-前端接口",description = "网站会员收藏服务")
public class MembersCollectController {
	@Autowired
	private MemberCollectNewsService memberCollectService;

	/**
	 * 会员新增收藏
	 * @param collect
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("会员新增收藏")
	public AjaxResponseBody add(@RequestBody MembersCollectNews collect) {
		if(collect.getMembersId() == null ) {
			return new AjaxResponseBody(false,Const.FAILED,"会员没有登陆，不能收藏");
		}
		if(collect.getPublishId() == null ) {
			return new AjaxResponseBody(false,Const.FAILED,"文章不存在，收藏失败");
		}
		boolean created = memberCollectService.save(collect);
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
	@ApiOperation("查询会员收藏列表")
	public AjaxResponseBody query(@RequestParam Integer memberId,
			@RequestParam Integer pageNum, 
			@RequestParam Integer pageSize){
		MembersCollectNews collect = new MembersCollectNews();
		collect.setMembersId(memberId);
		Page page = new Page(pageNum,pageSize);
		long total = memberCollectService.queryCount(collect);
		List<MembersCollectNews> list = memberCollectService.queryAll(collect,page);
		PageInfo<MembersCollectNews> pageInfo = new PageInfo<MembersCollectNews>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	/**
	 * 会员取消收藏
	 * @param comment
	 * @return
	 */
	@GetMapping("/delete")
	@ApiOperation("会员取消收藏")
	public AjaxResponseBody cancel(@RequestParam Integer collectId){
		boolean result = memberCollectService.cancelCollect(collectId);
		if(!result) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}

	
}
