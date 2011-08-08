package com.prime.bigpipe.web.component;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.prime.bigpipe.web.component.handler.ComponentHandler;

public class BigPipeComponentCallable implements Callable<JSONObject> {

	private final ComponentHandlerExecutor handlerExecutor;
	private final ComponentHandler handler;
	private final String componentName;
	private final Map<String, Object> params;
	private final HttpServletRequest request;
	private final HttpServletResponse response;

	public BigPipeComponentCallable(ComponentHandlerExecutor handlerExecutor, ComponentHandler handler, String componentName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
		this.handlerExecutor = handlerExecutor;
		this.handler = handler;
		this.componentName = componentName;
		this.params = params;
		this.request = request;
		this.response = response;
	}

	public ComponentHandlerExecutor getHandlerExecutor() {
		return handlerExecutor;
	}

	public ComponentHandler getHandler() {
		return handler;
	}

	public String getComponentName() {
		return componentName;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public JSONObject call() {
		ComponentResponse componentResponse = ComponentResponse.withStringWriter();
		handlerExecutor.execute(handler, componentName, params, request, componentResponse);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("content", componentResponse.getContent());
		jsonObject.element("name", componentName);

		return jsonObject;
	}

}
