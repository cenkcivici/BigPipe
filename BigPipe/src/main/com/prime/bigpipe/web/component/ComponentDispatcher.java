package com.prime.bigpipe.web.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prime.bigpipe.web.component.handler.ComponentHandler;

@Service
public class ComponentDispatcher {

	@Autowired
	private ComponentHandlerMapper componentHandlerMapper;

	@Autowired
	@Qualifier("bigPipeComponentHandlerExecutor")
	private ComponentHandlerExecutor componentHandlerExecutor;
	
	@Autowired
	private BigPipeExecutionService bigPipeExecutionService;

	public void dispatch(String componentName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
		ComponentHandler handler = componentHandlerMapper.findHandlerFor(componentName);
		componentHandlerExecutor.execute(handler,componentName,params,request,response);
	}
	
	public void flushComponents(HttpServletResponse response) {
		bigPipeExecutionService.flushComponents(response);
	}

}
