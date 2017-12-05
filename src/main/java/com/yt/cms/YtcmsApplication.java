package com.yt.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yt.cms.mapper")
public class YtcmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(YtcmsApplication.class, args);
	}
}
