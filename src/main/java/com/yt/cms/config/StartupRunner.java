package com.yt.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yt.cms.service.ResourceService;
@Component
public class StartupRunner implements CommandLineRunner {
	@Autowired
	private ResourceService resourceService;
	@Override
	public void run(String... args) throws Exception {
		resourceService.queryResource_W();
	}

}
