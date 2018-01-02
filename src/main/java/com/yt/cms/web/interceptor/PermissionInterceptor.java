package com.yt.cms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yt.cms.model.User;
import com.yt.cms.service.PermissionService;

/**
 * 
 */
public class PermissionInterceptor implements HandlerInterceptor {
	@Autowired
	private PermissionService permissionService;
	/**
	 * 在执行action里面的处理逻辑之前执行，它返回的是boolean，这里如果我们返回true在接着执行postHandle和afterCompletion，如果我们返回false则中断执行。
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);
        String requestURI = request.getRequestURI();
        System.out.println("请求类型： " + request.getMethod());
        System.out.println("pathInfo: " + request.getPathInfo());
        System.out.println("queryString:" + request.getQueryString());
        System.out.println("requestURL :" + request.getRequestURI());
        System.out.println(request.getRequestURI());
        //登录,退出不做拦截
        if(request.getRequestURI().equals("/login") || request.getRequestURI().equals("/logout")){
            return true;
        }
        //验证session是否存在
        User user = (User)request.getSession().getAttribute("_session_user");
        if(user == null){
        	// TODO 封装返回登陆页面的信息
//            response.sendRedirect("/user/login_view");
            return false;
        }
        Integer userId = user.getId(); 
        Integer userGroupId = user.getUserGroup().getId();
        //验证是否有权限访问
        if(!permissionService.hasPermission(userGroupId,userId, requestURI)) {
        	return false;
        }
        return true;
        
    }

    /**
     * 在执行action里面的逻辑后返回视图之前执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();

        // 打印方法执行时间
//        if (executeTime > 1000) {
//            System.out.println("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : "
//                    + executeTime + "ms");
//        } else {
//            System.out.println("[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "] 执行耗时 : "
//                    + executeTime + "ms");
//        }
    }
/**
 * 在action返回视图后执行。
 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
