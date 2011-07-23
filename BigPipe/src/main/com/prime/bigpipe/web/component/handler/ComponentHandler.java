package com.prime.bigpipe.web.component.handler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.prime.bigpipe.web.component.ComponentViewModel;

public abstract class  ComponentHandler {
	
	public abstract ComponentViewModel handle(Map<String, Object> params, HttpServletRequest request);
	
	//override and return true if cacheable
	public boolean isCacheable() {
		return false;
	}
	
	public String getCacheKeyFrom(String componentName, Map<String, Object> params, HttpServletRequest request) {
		return null;
	}
	
	//if cached means infinite
	public long getTtlInSeconds() {
		return 0;
	}
}
