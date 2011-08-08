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
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return viewModel;
	}

}
