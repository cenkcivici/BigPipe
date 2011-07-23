package com.prime.bigpipe.web.component.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.prime.bigpipe.web.component.ComponentViewModel;

public abstract class  ComponentHandler {
	public abstract ComponentViewModel handle(Map<String, Object> params, HttpServletRequest request);
	
	public boolean isCacheable() {
		return false;
	}
	public String getCacheKey() {
		return "";
	}
}
