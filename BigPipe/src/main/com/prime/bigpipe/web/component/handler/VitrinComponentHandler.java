package com.prime.bigpipe.web.component.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.prime.bigpipe.web.component.ComponentViewModel;

@Component
public class VitrinComponentHandler extends ComponentHandler {

	@Override
	public ComponentViewModel handle(Map<String, Object> params, HttpServletRequest request) {
		ComponentViewModel viewModel = new ComponentViewModel();
		viewModel.setView("vitrin");
		return viewModel;
	}
	
	@Override
	public boolean isCacheable() {
		return true;
	}
	
	@Override
	public long getTtlInSeconds() {
		return 100L;
	}
	
	@Override
	public String getCacheKeyFrom(String componentName, Map<String, Object> params, HttpServletRequest request) {
		return componentName;
	}

}
