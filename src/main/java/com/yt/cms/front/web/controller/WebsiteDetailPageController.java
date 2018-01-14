package com.yt.cms.front.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
import com.yt.cms.front.model.Article;
import com.yt.cms.front.model.Comments;
import com.yt.cms.front.service.WebsiteDetailPageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 网站详情页接口请求
 * @author admin
 *
 */
@RestController
@RequestMapping("/web/detail")
@Api(value = "网站详情页接口",description = "网站详情页服务")
public class WebsiteDetailPageController {
	@Autowired
	private WebsiteDetailPageService websiteDetailPageService;
	/**
	 * 查询网站详情页文章详情
	 * @return
	 */
	@GetMapping("/query/{websiteId}/{channelId}/{articleId}")
	@ApiOperation("查询网站详情页文章详情")
	public AjaxResponseBody queryArticleDetail(@PathVariable Integer websiteId,@PathVariable Integer channelId,@PathVariable Integer articleId){
		Article article = websiteDetailPageService.getWebsiteChannelNewsDetail(websiteId, channelId, articleId);
		return new AjaxResponseBody(true,Const.SUCCESS,article);
	}
	/**
	 * 查询网站详情页的评论数据
	 * 评论表需要添加网站id字段
	 * @return
	 */
	@GetMapping("/query/{websiteId}/{articleId}")
	@ApiOperation("查询网站详情页的评论数据")
	public AjaxResponseBody queryArticleComments(@PathVariable Integer websiteId,@PathVariable Integer articleId,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		long total = websiteDetailPageService.getArticleCommentNum(websiteId, articleId);
		Page page = new Page(pageNum,pageSize);
		List<Comments> list = websiteDetailPageService.getArticleComments(websiteId, articleId, page);
		PageInfo<Comments> pageInfo =  new PageInfo<Comments>(pageNum,pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	
	/**
	 * 查询网站详情页的评论总数
	 * 评论表需要添加网站id字段
	 * @return
	 */
	@GetMapping("/query/total/{websiteId}/{articleId}")
	@ApiOperation("查询网站详情页的评论总数")
	public AjaxResponseBody queryArticleCommentsTotal(@PathVariable Integer websiteId,@PathVariable Integer articleId,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		long total = websiteDetailPageService.getArticleCommentsTotal(websiteId, articleId);
		return new AjaxResponseBody(true,Const.SUCCESS,total);
	}
	
}
