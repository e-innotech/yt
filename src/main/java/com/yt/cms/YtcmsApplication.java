package com.yt.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@MapperScan("com.yt.cms.mapper")
@EnableCaching
public class YtcmsApplication implements CommandLineRunner{
	private Logger logger = LoggerFactory.getLogger(YtcmsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(YtcmsApplication.class, args);
	}
	/**
	 * CommandLineRunner 是服务启动之后执行一些操作可以实现该接口
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("服务启动完成!");
	}
}
