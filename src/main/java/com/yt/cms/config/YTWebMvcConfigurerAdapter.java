package com.yt.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yt.cms.web.interceptor.PermissionInterceptor;


@Configuration
public class YTWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Bean
    public PermissionInterceptor getPermissionInterceptor(){
        return new PermissionInterceptor();
    }
    /**
     * 配置静态访问资源
     * @param registry
     */
/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //自定义项目内目录
        //registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
        //指向外部目录
        registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/");
        super.addResourceHandlers(registry);
    }*/

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     *
     * @param registry
     */
/*    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
        super.addViewControllers(registry);
    }*/

	 @Override  
	    public void configurePathMatch(PathMatchConfigurer configurer) {  
	        super.configurePathMatch(configurer);  
	        configurer.setUseSuffixPatternMatch(false);  
	    }
    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(getPermissionInterceptor()).addPathPatterns("/**").excludePathPatterns("/logout","/login","/swagger-resources/**");
        super.addInterceptors(registry);
    }
}
