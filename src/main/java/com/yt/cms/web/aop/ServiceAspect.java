package com.yt.cms.web.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*@Aspect
@Component*/
public class ServiceAspect {
	private Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
//	 @Pointcut("execution(public * com.yt.cms.service.impl.WebsitesServiceImpl.*(..))")
	    public void webLog() {
	    }

	    @Before("webLog()")
	    public void doBefore(JoinPoint joinPoint) throws Throwable {
	        System.out.println( "进入doBefore切面");
	        // 接收到请求，记录请求内容
	        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        HttpServletRequest request = attributes.getRequest();

	        // 记录下请求内容
	        logger.info("URL : " + request.getRequestURL().toString());
	        logger.info("HTTP_METHOD : " + request.getMethod());
	        logger.info("IP : " + request.getRemoteAddr());
	        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

	    }
}
