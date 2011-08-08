package com.prime.bigpipe.web.component;

import javax.servlet.http.HttpServletResponse;

public interface BigPipeExecutionService {
	void registerComponentCall(BigPipeComponentCallable runnable);
	void flushComponents(HttpServletResponse response);
}
