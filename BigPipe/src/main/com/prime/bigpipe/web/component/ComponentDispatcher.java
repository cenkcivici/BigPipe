package com.prime.bigpipe.web.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	

	public String dispatch(String componentName, Map<String, Object> params, HttpServletRequest request) {
		ComponentHandler handler = componentHandlerMapper.findHandlerFor(componentName);
		return componentHandlerExecutor.execute(handler,componentName,params,request);
	}
}
