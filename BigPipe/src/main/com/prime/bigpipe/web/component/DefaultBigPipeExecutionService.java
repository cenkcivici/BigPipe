package com.prime.bigpipe.web.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;

public class DefaultBigPipeExecutionService implements BigPipeExecutionService {

	private List<Future> componentsInPipe = new ArrayList<Future>();

	@Autowired
	private ExecutorService executorService;

	@Override
	public void registerComponentCall(BigPipeComponentRunnable runnable) {
		Future<?> future = executorService.submit(runnable);
		componentsInPipe.add(future);
	}

	@Override
	public void flushAll() {
		for (Future eachFuture : componentsInPipe) {
			try {
				eachFuture.get(10000L, TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
