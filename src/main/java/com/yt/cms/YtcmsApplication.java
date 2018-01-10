package com.yt.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 去掉find 的接口，并入query
 * 分页自己实现
 * @author admin
 *
 */
@SpringBootApplication
@MapperScan("com.yt.cms.mapper")
@EnableCaching
@EnableTransactionManagement
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
	
	@Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
		logger.info(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
