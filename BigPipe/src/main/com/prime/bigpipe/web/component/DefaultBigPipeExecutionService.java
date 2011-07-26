package com.prime.bigpipe.web.component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

//lives in request scope
public class DefaultBigPipeExecutionService implements BigPipeExecutionService {

	private CompletionService<?> executionCompletionService;
	
	private BlockingQueue<Future<?>> blockingQueue = new LinkedBlockingQueue<Future<?>>();
	private int countOfRunnablesSubmitted;
	
	@Autowired
	private ExecutorService executorService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostConstruct
	public void init() {
		executionCompletionService = new ExecutorCompletionService(executorService,blockingQueue);
	}

	@Override
	public void registerComponentCall(BigPipeComponentRunnable runnable) {
		executionCompletionService.submit(runnable,null);
		countOfRunnablesSubmitted++;
	}

	@Override
	public void waitForLastComponentToFinish() {
		//TODO a better wait method?
		while(hasRunningComponentThreads()) {
			Thread.yield();
		}
	}

	private boolean hasRunningComponentThreads() {
		return countOfRunnablesSubmitted > 0 && blockingQueue.size() < countOfRunnablesSubmitted;
	}

}
