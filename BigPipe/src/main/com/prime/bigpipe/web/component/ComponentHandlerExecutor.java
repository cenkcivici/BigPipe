package com.prime.bigpipe.web.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prime.bigpipe.web.component.handler.ComponentHandler;

public interface ComponentHandlerExecutor {
	void execute(ComponentHandler handler, String componentName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response);
}
