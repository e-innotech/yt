package com.yt.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
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
	public HttpEntity<?> add(@RequestBody MembersCollectNews collect) {
		boolean created = memberCollectService.save(collect);
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
	@ApiOperation("查询会员收藏列表")
	public List<MembersCollectNews> query(){
		MembersCollectNews collect = new MembersCollectNews();
		return memberCollectService.queryAll(collect);
	}

	/**
	 * 会员取消收藏
	 * @param comment
	 * @return
	 */
	@DeleteMapping("/delete/{collectId}")
	@ApiOperation("会员取消收藏")
	public HttpEntity<?> delete(@PathVariable Integer collectId){
		boolean result = memberCollectService.delete(collectId);
		if(!result) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.OK);
	}

	
}
