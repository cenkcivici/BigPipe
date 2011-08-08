package com.prime.bigpipe.web.component;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

//lives in request scope
public class DefaultBigPipeExecutionService implements BigPipeExecutionService {

	private CompletionService<JSONObject> executionCompletionService;

	private BlockingQueue<Future<JSONObject>> blockingQueue = new LinkedBlockingQueue<Future<JSONObject>>();
	private int countOfRunnables;

	@Autowired
	private ExecutorService executorService;

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
	public void flushComponents(HttpServletResponse response) {
		try {
			while (countOfRunnables > 0) {
				Future<JSONObject> future = blockingQueue.take();
				JSONObject jsonObject = future.get();

				PrintWriter writer = response.getWriter();
				writer.write("<script>bigpipe.registerComponent(");
				writer.write(jsonObject.toString());
				writer.write(");</script>");

				countOfRunnables--;
				writer.flush();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
