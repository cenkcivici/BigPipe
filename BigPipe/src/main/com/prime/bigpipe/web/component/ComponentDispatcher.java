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
	@Qualifier("defaultComponentHandlerExecutor")
	private ComponentHandlerExecutor componentHandlerExecutor;
	
	@Autowired
	private BigPipeExecutionService bigPipeExecutionService;

	public String dispatch(String componentName, Map<String, Object> params, HttpServletRequest request) {
		ComponentHandler handler = componentHandlerMapper.findHandlerFor(componentName);
		return componentHandlerExecutor.execute(handler,componentName,params,request);
	}
	
	public void flushComponents(HttpServletResponse response) {
		bigPipeExecutionService.flushComponents(response);
	}

}
