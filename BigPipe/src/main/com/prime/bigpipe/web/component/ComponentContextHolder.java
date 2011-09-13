package com.prime.bigpipe.web.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComponentContextHolder {

	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}


	public void initWith(HttpServletRequest request) {
		this.request = request;
	}

}
