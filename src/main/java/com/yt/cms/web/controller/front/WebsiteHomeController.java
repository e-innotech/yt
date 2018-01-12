package com.yt.cms.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 网站首页请求接口
 * @author admin
 *
 */
@Controller
public class WebsiteHomeController {
	

	
	@GetMapping("/index")
	String index(Model model) {
		model.addAttribute("msg", "Spring Boot AJAX Example,hhhhh");
//		return "yynews/html/index";
		return "/yynews/html/index";
	}
	
}
