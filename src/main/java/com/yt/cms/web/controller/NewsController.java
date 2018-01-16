package com.yt.cms.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
import com.yt.cms.common.DateUtil;
import com.yt.cms.common.Page;
import com.yt.cms.common.PageInfo;
import com.yt.cms.model.AduitNews;
import com.yt.cms.model.News;
import com.yt.cms.model.NewsLaunch;
import com.yt.cms.model.NewsPublish;
import com.yt.cms.model.NewsPublishLine;
import com.yt.cms.model.User;
import com.yt.cms.model.Websites;
import com.yt.cms.service.NewsLaunchService;
import com.yt.cms.service.NewsPublishService;
import com.yt.cms.service.NewsService;
import com.yt.cms.service.WebsitesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/news")
@Api(value = "稿件服务")
public class NewsController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsLaunchService newsLaunchService;
	@Autowired
	private NewsPublishService newsPublishService;
	@Autowired
	private WebsitesService websitesService;
	/**
	 * 列表页面，只可查询status=0 未投放的稿件
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询稿件列表")
	public AjaxResponseBody query(@RequestParam(required=false) String newsTitle,
			@RequestParam(required=false) String source,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		News news = new News();
		news.setNewsTitle(newsTitle);
		news.setSource(source);
		news.setStartDate(startDate);
		news.setEndDate(endDate);
		Page page = new Page(pageNum,pageSize);
		long total = newsService.queryCount(news);
		List<News> list = newsService.queryAll(news,page);
		PageInfo<News> pageInfo = new PageInfo<News>(pageNum, pageSize,total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/id")
	@ApiOperation("按照id查询稿件")
	public AjaxResponseBody findById(@RequestParam Integer id) {
		News result = newsService.findById(id);
		return new AjaxResponseBody(true,Const.SUCCESS,result);
	}
	/**
	 * 新增稿件
	 * @param Article
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加稿件")
	@LogAnnotation(action="新增稿件")
	public AjaxResponseBody add(@Valid @RequestBody News news,  HttpServletRequest request,BindingResult result) {
		HttpSession session = request.getSession();
		User user_session = (User) session.getAttribute(Const.SESSION_USER_KEY);
		if(user_session == null || user_session.getId() == null) {
			return  new AjaxResponseBody(false,Const.SESSION_TIMEOUT,null);
		} 
		news.setSubmitUserId(user_session.getId());
		boolean created = newsService.save(news);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 修改稿件,status = 0才可修改
	 * @param Article
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation("修改稿件")
	@LogAnnotation(action="修改稿件")
	public AjaxResponseBody update(@RequestBody News news){
		boolean created = newsService.update(news);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 不能删除稿件
	 * @param id
	 * @return
	 */
/*	@GetMapping("/delete")
	@ApiOperation("删除稿件")
	public AjaxResponseBody delete(@RequestParam Integer id){
		boolean created = newsService.deleteLogicById(id);
		if(!created) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}*/
	
	/**
	 * 投放稿件到网站和栏目
	 * 同一个稿件一次投放可以投放多个网站，每个网站可以选择多个栏目
	 * TODO 如果再次投放是否还可以选择之前投放的网站和栏目？如果可以那么现在的表结构需要修改（不修改需要关联发布表来判断）
	 * @return
	 */
	@PostMapping("/launch/add")
	@ApiOperation("投放稿件到网站和栏目")
	@LogAnnotation(action="投放稿件")
	public AjaxResponseBody launch(@RequestBody NewsLaunch newsLaunch, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user_session = (User) session.getAttribute(Const.SESSION_USER_KEY);
		if(user_session == null || user_session.getId() == null) {
			return  new AjaxResponseBody(false,Const.SESSION_TIMEOUT,null);
		} 
		newsLaunch.setCreateUserId(user_session.getId());
		boolean release =  newsLaunchService.save(newsLaunch);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}


	/**
	 * 编辑稿件投放网站与栏目
	 * 只有审批状态是审批不通过的才可以修改 投放的网站
	 * @return
	 */
	@PostMapping("/launch/update")
	@ApiOperation("编辑稿件投放网站与栏目")
	@LogAnnotation(action="编辑稿件投放")
	public AjaxResponseBody updateLaunch(@RequestBody NewsLaunch newsLaunch){
		
		//  可以修改news 表的内容
		boolean release =  newsLaunchService.update(newsLaunch);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 按照稿件投放id查询稿件投放详情
	 * @return
	 */
	@GetMapping("/launch/find/id")
	@ApiOperation("按照稿件投放id查询稿件投放详情")
	public AjaxResponseBody findLaunch(@RequestParam Integer id){
		NewsLaunch module =  newsLaunchService.findById(id);
		if(module == null) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,module);
	}
	
	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/launch/query")
	@ApiOperation("查询稿件列表")
	public AjaxResponseBody queryLaunch(@RequestParam(required=false) String newsTitle,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		NewsLaunch newsLaunch = new NewsLaunch();
		newsLaunch.setNewsTitle(newsTitle);
		newsLaunch.setStartDate(startDate);
		newsLaunch.setEndDate(endDate);
		Page page = new Page(pageNum,pageSize);
		long total = newsLaunchService.queryCount(newsLaunch);
		List<NewsLaunch> list = newsLaunchService.queryAll(newsLaunch,page);
		PageInfo<NewsLaunch> pageInfo = new PageInfo<NewsLaunch>(pageNum,pageSize, total,list);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	}
	/**
	 * 不能删除稿件投放
	 * @return
	 */
	/*@GetMapping("/launch/delete")
	@ApiOperation("按照稿件投放id删除")
	public AjaxResponseBody deleteLaunch(@RequestParam Integer id){
		boolean release =  newsLaunchService.deleteLogicById(id);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}*/
	/**
	 * 稿件审批
	 * @return
	 */
	@PostMapping("/launch/aduit")
	@ApiOperation("审批稿件")
	@LogAnnotation(action="审批稿件")
	public AjaxResponseBody aduit(@RequestBody AduitNews aduit,HttpSession session){
		// 从数据库中拿newsId 和 NewsLaunchConfig
		NewsLaunch newsLaunch = newsLaunchService.findById(aduit.getId());
		BeanUtils.copyProperties(aduit, newsLaunch);
		User user = (User) session.getAttribute(Const.SESSION_USER_KEY);
		if(user == null) {
			return new AjaxResponseBody(false,Const.SESSION_TIMEOUT,null);
		}
		// 从session中拿当前用户id
		newsLaunch.setAduitUserId(user.getId());
		newsLaunch.setAduitDate(new Date());
		boolean release =  newsLaunchService.aduit(newsLaunch);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	/**
	 * 按照稿件发布id查询稿件投放详情
	 * @return
	 */
	@GetMapping("/publish/find/id")
	@ApiOperation("按照稿件发布id查询稿件投放详情")
	public AjaxResponseBody findPublish(@RequestParam Integer id){
		NewsPublishLine module =  newsPublishService.findById(id);
		if(module == null) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,module);
	}
	
	/**
	 * 查询所有稿件投放信息
	 * @return
	 */
	@GetMapping("/publish/query")
	@ApiOperation("查询所有稿件投放信息")
	public AjaxResponseBody queryPublish(@RequestParam(required=false) String websiteName,
			@RequestParam(required=false) String channelName,
			@RequestParam(required=false) Integer isline,
			@RequestParam(required=false) Integer ishome,
			@RequestParam Integer pageNum,
			@RequestParam Integer pageSize){
		
		NewsPublish newsPublish = new NewsPublish();
		newsPublish.setChannelName(channelName);
		newsPublish.setWebsiteName(websiteName);
		newsPublish.setIshome(ishome);
		newsPublish.setIsline(isline);
		Page page = new Page(pageNum,pageSize);
		long total = newsPublishService.queryCount(newsPublish);
		List<NewsPublish> modules =  newsPublishService.query(newsPublish,page);
		PageInfo<NewsPublish> pageInfo =  new PageInfo<NewsPublish>(pageNum,pageSize, total,modules);
		return new AjaxResponseBody(true,Const.SUCCESS,pageInfo);
	
	}
	/**
	 * 稿件上下线
	 * @return
	 */
	@GetMapping("/publish/offLine")
	@ApiOperation("稿件上下线")
	@LogAnnotation(action="稿件上下线")
	public AjaxResponseBody publishOffLine(@RequestParam Integer id, @RequestParam Integer lineStatus){
		NewsPublish publish = new NewsPublish();
		publish.setId(id);
		// 前端传递要修改的状态
		publish.setIsline(lineStatus);
		publish.setOfflineDate(DateUtil.getDateStr(new Date()));
		
		boolean release =  newsPublishService.update(publish);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
	/**
	 * 设置首页
	 * 
	 * @return
	 */
	// TODO 判断是否超过网站的首页权重值
	@GetMapping("/publish/setHome")
	@ApiOperation("设置首页")
	@LogAnnotation(action="设置首页")
	public AjaxResponseBody setHome(@RequestParam Integer id, @RequestParam Integer isHome,@RequestParam Integer homeWeight){
		NewsPublish db_publish = newsPublishService.findByPublishId(id);
		// 先查询该网站设置的最大权重值
		if(db_publish == null || db_publish.getWebsiteId() == null) {
			return new AjaxResponseBody(false,Const.FAILED,"此发布信息的网站不存在，不能置首页");
		}
		Websites web = websitesService.findById(db_publish.getWebsiteId());
		if(web == null || web.getHomeWeightMax() == null || web.getHomeWeightMax().intValue() < homeWeight.intValue()) {
			return new AjaxResponseBody(false,Const.FAILED,"设置的权重值不能大于网站设置的最大权重值");
		}
		NewsPublish publish = new NewsPublish();
		publish.setId(id);
		// 前端传递要修改的状态
		publish.setHomeWeight(homeWeight);
		publish.setIshome(isHome);
		boolean release =  newsPublishService.update(publish);
		if(!release) {
			return new AjaxResponseBody(false,Const.FAILED,null);
		}
		return new AjaxResponseBody(true,Const.SUCCESS,null);
	}
	
}
