package com.yt.cms.web.controller.front;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 网站栏目页请求
 * @author admin
 *
 */
@RestController
@RequestMapping("/web/channel")
@Api(value = "前端网站栏目接口")
public class WebsiteChannelController {

	/**
	 * 查询网站栏目
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询网站栏目")
	public AjaxResponseBody query(@RequestParam String websiteId){
		
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
}
