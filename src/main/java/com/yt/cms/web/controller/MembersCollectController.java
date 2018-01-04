package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.model.MembersCollectNews;
import com.yt.cms.service.MemberCollectNewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member/collect")
@Api(value = "会员收藏服务")
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
	public AjaxResponseBody query(@RequestParam Integer memberId, Page page){
		MembersCollectNews collect = new MembersCollectNews();
		collect.setMembersId(memberId);
		collect.setStatus(0);
		List<MembersCollectNews> list = memberCollectService.queryAll(collect,page);
		PageInfo<MembersCollectNews> pageInfo = new PageInfo<MembersCollectNews>(list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	/**
	 * 会员取消收藏
	 * @param comment
	 * @return
	 */
	@PutMapping("/delete")
	@ApiOperation("会员取消收藏")
	public AjaxResponseBody cancel(@RequestParam Integer collectId){
		boolean result = memberCollectService.cancelCollect(collectId);
		if(!result) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}

	
}
