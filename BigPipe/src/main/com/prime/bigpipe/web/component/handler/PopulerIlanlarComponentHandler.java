package com.prime.bigpipe.web.component.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.prime.bigpipe.web.component.ComponentViewModel;

@Component
public class PopulerIlanlarComponentHandler implements ComponentHandler {

	@Override
	public ComponentViewModel handle(Map<String, Object> params, HttpServletRequest request) {
		ComponentViewModel viewModel = new ComponentViewModel("populerIlanlar");
		return viewModel;
	}

}
