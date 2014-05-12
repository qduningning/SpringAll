package com.znn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.znn.json.controller.UserJson;

public class LogInterceptor implements HandlerInterceptor {
	static final Logger logger = 	LogManager.getLogger(UserJson.class);
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		System.out.println("preHandle");
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());//
		System.out.println(request.getQueryString());
		System.out.println(request.getHeader("User-Agent"));
		System.out.println(request.getMethod());
//		System.out.println(request.getPathInfo());
//		logger.info("");
		logger.info("URI:"+request.getRequestURI()+",Parameter:"+request.getQueryString()+","+request.getMethod()+","+request.getHeader("User-Agent"));
		return true;
	}

}
