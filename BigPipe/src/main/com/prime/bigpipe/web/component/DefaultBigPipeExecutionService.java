package com.prime.bigpipe.web.component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

//lives in request scope
public class DefaultBigPipeExecutionService implements BigPipeExecutionService {

	private CompletionService<JSONObject> executionCompletionService;

	private BlockingQueue<Future<JSONObject>> blockingQueue = new LinkedBlockingQueue<Future<JSONObject>>();
	private int countOfRunnables;

	@Autowired
	private ExecutorService executorService;

	public int getCountOfRunnables() {
		return countOfRunnables;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostConstruct
	public void init() {
		executionCompletionService = new ExecutorCompletionService(executorService, blockingQueue);
	}

	@Override
	public void registerComponentCall(BigPipeComponentCallable callable) {
		executionCompletionService.submit(callable);
		countOfRunnables++;
	}
	
	@Override
	public JSONObject getNextFinishedComponent() {
		try {
			Future<JSONObject> future =  blockingQueue.take();
			JSONObject jsonObject = future.get();
			return jsonObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void oneLessRunnable() {
		countOfRunnables--;
		
	}

}
