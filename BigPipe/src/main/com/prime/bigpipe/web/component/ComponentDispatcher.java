package com.prime.bigpipe.web.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.bigpipe.web.component.handler.ComponentHandler;

@Service
public class ComponentDispatcher {

	@Autowired
	private ComponentHandlerMapper componentHandlerMapper;

	@Autowired
	private ComponentRenderer componentRenderer;

	public void dispatch(String componentName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
		ComponentHandler handler = componentHandlerMapper.findHandlerFor(componentName);
		ComponentViewModel viewModel = handler.handle(params, request);
		componentRenderer.render(viewModel, request, response);
	}

}
