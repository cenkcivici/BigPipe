package com.prime.bigpipe.web.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.prime.bigpipe.web.component.ComponentContextHolder;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ComponentContextHolder contextHolder;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		contextHolder.initWith(request);
		modelAndView.addObject("_request", request);
		modelAndView.addObject("_response", response);
		super.postHandle(request, response, handler, modelAndView);
	}
}