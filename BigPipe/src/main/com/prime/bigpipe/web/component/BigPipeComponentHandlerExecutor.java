package com.prime.bigpipe.web.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prime.bigpipe.web.component.handler.ComponentHandler;

@Service
public class BigPipeComponentHandlerExecutor implements ComponentHandlerExecutor {

	@Autowired
	@Qualifier("defaultComponentHandlerExecutor")
	private ComponentHandlerExecutor defaultComponentHandlerExecutor;
	
	@Autowired
	@Qualifier("cachingComponentHandlerExecutor")
	private ComponentHandlerExecutor cachingComponentHandlerExecutor;
	
	@Autowired
	private BigPipeExecutionService bigPipeExecutionService;
	
	@Override
	public void execute(ComponentHandler handler, String componentName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
		
		if (handler.isCacheable()) {
			//dont async if cacheable
			cachingComponentHandlerExecutor.execute(handler, componentName, params, request, response);
		} else {
			BigPipeComponentRunnable bigPipeContext = new BigPipeComponentRunnable(defaultComponentHandlerExecutor,handler,componentName,params,request,response);
			bigPipeExecutionService.registerComponentCall(bigPipeContext);
		}
		
		
		
		
	}

}
