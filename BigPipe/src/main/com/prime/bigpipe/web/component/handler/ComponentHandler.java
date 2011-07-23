package com.prime.bigpipe.web.component.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.prime.bigpipe.web.component.ComponentViewModel;

public interface ComponentHandler {
	ComponentViewModel handle(Map<String, Object> params, HttpServletRequest request);
}
