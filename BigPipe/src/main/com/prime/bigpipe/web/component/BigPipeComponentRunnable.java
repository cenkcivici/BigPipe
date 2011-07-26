package com.prime.bigpipe.web.component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.prime.bigpipe.web.component.handler.ComponentHandler;

public class BigPipeComponentRunnable implements Runnable {

	private final ComponentHandlerExecutor handlerExecutor;
	private final ComponentHandler handler;
	private final String componentName;
	private final Map<String, Object> params;
	private final HttpServletRequest request;
	private final HttpServletResponse response;

	public BigPipeComponentRunnable(ComponentHandlerExecutor handlerExecutor, ComponentHandler handler, String componentName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
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

	public void run() {
		ComponentResponse componentResponse = ComponentResponse.withStringWriter();
		handlerExecutor.execute(handler, componentName, params, request, componentResponse);
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("content", componentResponse.getContent());
			jsonObject.element("name", componentName);
			
			PrintWriter writer = response.getWriter();
			writer.write("<script>bigpipe.registerComponent(");
			writer.write(jsonObject.toString());
			writer.write(");</script>");
			
			synchronized (writer) {
				response.flushBuffer();
				response.resetBuffer();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
