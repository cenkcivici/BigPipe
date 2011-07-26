package com.prime.bigpipe.web.component;

public interface BigPipeExecutionService {
	void registerComponentCall(BigPipeComponentRunnable runnable);
	void waitForLastComponentToFinish();
}
