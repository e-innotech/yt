package com.yt.cms.front.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.front.model.Ad;
import com.yt.cms.front.model.Channel;
import com.yt.cms.front.service.WebsiteCommonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 网站通用请求接口
 * @author admin
 *
 */
@RestController
@Api(value = "网站通用服务",description = "网站通用服务")
public class WebsiteCommonController {
	@Autowired
	private WebsiteCommonService websiteCommonService;

	/**
	 * 通用模块接口-广告
	 * @param websiteId
	 * @param homeWeight
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/common/ad/{websiteId}/{templateType}")
	@ApiOperation("通用接口-广告")
	public AjaxResponseBody queryCommonAd(@PathVariable Integer websiteId,
			@PathVariable Integer homeWeight){
		List<Ad> ads = websiteCommonService.getWebsiteTemplateAds(websiteId, homeWeight);
		return new AjaxResponseBody(true,Const.SUCCESS,ads);
	}
	
	/**
	 * 通用模块接口-网站栏目
	 * @param websiteId
	 * @param homeWeight
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/common/channel/{websiteId}")
	@ApiOperation("通用接口-网站栏目")
	public AjaxResponseBody queryCommonChannel(@PathVariable Integer websiteId){
		List<Channel> channel = websiteCommonService.getWebsiteChannel(websiteId);
		return new AjaxResponseBody(true,Const.SUCCESS,channel);
	}
}
