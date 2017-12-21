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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;
import com.yt.cms.model.AduitNews;
import com.yt.cms.model.News;
import com.yt.cms.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/news")
@Api(value = "稿件服务")
public class NewsController {
	@Autowired
	private NewsService newsService;

	/**
	 * 列表页面
	 * @return
	 */
	@GetMapping("/query")
	@ApiOperation("查询稿件列表")
	public List<News> query(){
		News News = new News();
		return newsService.queryAll(News);
	}

	/**
	 * 按照id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/find/{id}")
	@ApiOperation("按照id查询稿件")
	public HttpEntity<?> findById(@PathVariable Integer id) {
		News result = newsService.findById(id);
		HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<News>(result, status);
	}
	/**
	 * 新增稿件
	 * @param News
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation("添加稿件")
	public HttpEntity<?> add(@RequestBody News News) {
		boolean created = newsService.save(News);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 修改稿件
	 * @param News
	 * @return
	 */
	@PutMapping("/update")
	@ApiOperation("修改稿件")
	public HttpEntity<?> update(@RequestBody News News){
		boolean created = newsService.update(News);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	/**
	 * 删除稿件
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@ApiOperation("删除稿件")
	public HttpEntity<?> delete(@PathVariable Integer id){
		boolean created = newsService.delete(id);
		if(!created) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	/**
	 * 投放稿件到网站
	 * @return
	 */
	@PostMapping("/launch/{newsId}")
	@ApiOperation("投放稿件到网站")
	public HttpEntity<?> launch(@PathVariable Integer newsId, @RequestBody List<Integer> websitesId){
		boolean release =  newsService.launch(newsId, websitesId);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}

	/**
	 * 稿件审批
	 * @return
	 */
	@PutMapping("/aduit")
	@ApiOperation("投放稿件到网站")
	public HttpEntity<?> aduit(@RequestBody AduitNews aduit){
		News news = new News();
		news.setId(aduit.getId());
		news.setAduitDes(aduit.getAduitDesc());
		// 从session中拿当前用户id
//		news.setAduitUserId(aduitUserId);
		news.setStatus(aduit.getStatus());
		boolean release =  newsService.update(news);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
	/**
	 * 稿件上下线
	 * @return
	 */
	@PutMapping("/ofLine/{newsId}/{line}")
	@ApiOperation("投放稿件到网站")
	public HttpEntity<?> ofLine(@PathVariable Integer newsId, Integer line ){
		News news = new News();
		news.setId(newsId);
		// 从session中拿当前用户id
//		news.setStatus(aduit.getStatus());
		boolean release =  newsService.update(news);
		if(!release) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		AjaxResponseBody response = new AjaxResponseBody();
		response.setMsg(Const.SUCCESS);
		return new ResponseEntity<AjaxResponseBody>(response,HttpStatus.CREATED);
	}
	
		
	
}
