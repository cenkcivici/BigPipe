package com.prime.bigpipe.web.component;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.prime.bigpipe.service.CacheManager;
import com.prime.bigpipe.web.component.handler.ComponentHandler;

@Service
public class CachingComponentHandlerExecutor implements ComponentHandlerExecutor {

	@Autowired
	private ComponentHandlerExecutor handlerExecutorToDecorate;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public void execute(ComponentHandler handler, String componentName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (handler.isCacheable()) {
				String key = handler.getCacheKeyFrom(componentName, params, request);
				Assert.notNull(key);

				String content = cacheManager.get(key);

				if (StringUtils.isEmpty(content)) {
					ComponentResponse componentResponse = ComponentResponse.withStringWriter();
					handlerExecutorToDecorate.execute(handler, componentName, params, request, componentResponse);
					cacheManager.put(key, componentResponse.getContent(), handler.getTtlInSeconds());
					content = componentResponse.getContent();
				}

				response.getWriter().write(content);
			} else {
				handlerExecutorToDecorate.execute(handler, componentName, params, request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
