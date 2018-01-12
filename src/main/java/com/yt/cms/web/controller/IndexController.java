package com.yt.cms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
	String index(Model model) {
		model.addAttribute("msg", "Spring Boot AJAX Example");
		return "ajax";
	}

	
	
}
