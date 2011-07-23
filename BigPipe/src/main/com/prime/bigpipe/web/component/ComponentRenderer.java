package com.prime.bigpipe.web.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

@Service
public class ComponentRenderer {
	
	@Autowired
	private ViewResolver viewResolver;
	

	public void render(ComponentViewModel viewModel,HttpServletRequest request, HttpServletResponse response) {
		try {
			View resolvedView = viewResolver.resolveViewName("components/" + viewModel.getView(), Locale.ENGLISH);
			resolvedView.render(viewModel.getModel(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
