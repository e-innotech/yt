package com.yt.cms.web.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yt.cms.annotations.LogAnnotation;
import com.yt.cms.common.Const;
import com.yt.cms.mapper.UserLogsMapper;
import com.yt.cms.model.ResourceW;
import com.yt.cms.model.User;
import com.yt.cms.model.UserLogs;
import com.yt.cms.service.ResourceService;
/**
 * 
 * 拦截 广告，广告位，channel，会员状态修改，

add  新增
update 修改
delete 删除


find/id 按照id查询
find/name
query 分页查询
 * 
 * 
 * 
 * @author admin
 *
 */
@Aspect
@Component
public class UserLogAspect {

	 private final Logger logger = LoggerFactory.getLogger(UserLogAspect.class);
	 private final String LOG_ACTION = "action";
	    @Autowired
	    private UserLogsMapper logDAO;
	    @Autowired
		private ResourceService resourceService;
	    @Pointcut("@annotation(com.yt.cms.annotations.LogAnnotation)")
	    private void pointCutMethod(){}

	    /**
	     * 记录操作日志
	     */
	    @After("pointCutMethod()")  // 使用上面定义的切入点
	    public void recordLog(JoinPoint joinPoint){
	        Long start = System.currentTimeMillis();
	        UserLogs userLog = new UserLogs();
	        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	        User user = (User) request.getSession().getAttribute(Const.SESSION_USER_KEY);
	        if(user == null){
	            logger.warn("user 信息为空");
	        }else{
	        	userLog.setUsersId(user.getId());
	        	List<ResourceW> list = resourceService.queryResource_W();
	        	String uri = request.getRequestURI();
	        	for(ResourceW w : list) {
	        		if(uri.equals(w.getUri())) {
	        			userLog.setResourceId(w.getId());
	        			break;
	        		}
	        	}
	        }
	        try {
	            Map<String,String> map = getLogMark(joinPoint);
	            userLog.setAction(map.get(LOG_ACTION));
	            logDAO.insertSelective(userLog);
	        }catch (ClassNotFoundException c){
	            logger.error(c.getMessage());
	        }catch (Exception e){
	            logger.error("插入日志异常",e.getMessage());
	        }
	        Long end = System.currentTimeMillis();
	        logger.info("记录日志消耗时间:"+ (end - start) / 1000);
	    }

	    private Map<String,String> getLogMark(JoinPoint joinPoint) throws ClassNotFoundException {
	        Map<String,String> map = new HashMap<>();
	        String methodName = joinPoint.getSignature().getName();
	        String targetName = joinPoint.getTarget().getClass().getName();
	        Class targetClass = Class.forName(targetName);
	        Method[] methods = targetClass.getMethods();
	        for (Method method : methods){
	            if(method.getName().equals(methodName)){
	                LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
	                map.put(LOG_ACTION,logAnnotation.action());
	                break;
	            }
	        }
	        return map;
	    }
	    @Before("pointCutMethod()")
	    public void validParam(JoinPoint joinPoint) {
	    }
}
