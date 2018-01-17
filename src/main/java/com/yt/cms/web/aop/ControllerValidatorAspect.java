package com.yt.cms.web.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.yt.cms.common.AjaxResponseBody;
import com.yt.cms.common.Const;

@Aspect
@Component
public class ControllerValidatorAspect {

//	@Autowired
//	private MessageSource messageSource;

	@Around("execution(* com.yt.cms.web.controller.*.*(..)) && args(..,bindingResult)")
	public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
		Object retVal;
		if (bindingResult.hasErrors()) {
			retVal = doErrorHandle(bindingResult);
		} else {
			retVal = pjp.proceed();
		}
		return retVal;
	}

	private Object doErrorHandle(BindingResult bindingResult) {
		StringBuffer msg = new StringBuffer();
		// 获取错误字段集合
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		// 获取本地locale,zh_CN
//		Locale currentLocale = LocaleContextHolder.getLocale();
		// 遍历错误字段获取错误消息
		for (FieldError fieldError : fieldErrors) {
			// 获取错误信息
//			String errorMessage = messageSource.getMessage(fieldError, currentLocale);
			// 添加到错误消息集合内
			msg.append(fieldError.getDefaultMessage() + " ; ");
		}
		// 截取最后一个" , "
		String data = msg.subSequence(0, msg.lastIndexOf(" ; ")).toString();
		return new AjaxResponseBody(false, Const.FAILED, data);

	}
}
