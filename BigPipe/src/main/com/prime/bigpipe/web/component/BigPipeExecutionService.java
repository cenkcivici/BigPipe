package com.prime.bigpipe.web.component;

public interface BigPipeExecutionService {

	public abstract void registerComponentCall(BigPipeComponentRunnable runnable);

	public abstract void flushAll();

}
