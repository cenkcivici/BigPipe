package com.prime.bigpipe.web.component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.prime.bigpipe.service.CacheManager;
import com.prime.bigpipe.web.component.handler.ComponentHandler;

@Service
public class CachingComponentHandlerExecutor implements ComponentHandlerExecutor {

	@Autowired
	@Qualifier("defaultComponentHandlerExecutor")
	private ComponentHandlerExecutor handlerExecutorToDecorate;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public String execute(ComponentHandler handler, String componentName, Map<String, Object> params, HttpServletRequest request) {
		if (handler.isCacheable()) {
			String key = handler.getCacheKeyFrom(componentName, params, request);
			Assert.notNull(key);

			String content = cacheManager.get(key);

			if (StringUtils.isEmpty(content)) {
				content = handlerExecutorToDecorate.execute(handler, componentName, params, request);
				cacheManager.put(key, content, handler.getTtlInSeconds());
			}
			return content;
		} else {
			return handlerExecutorToDecorate.execute(handler, componentName, params, request);
		}
	}
}
