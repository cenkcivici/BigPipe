package com.prime.bigpipe.web.component;

import net.sf.json.JSONObject;

public interface BigPipeExecutionService {
	void registerComponentCall(BigPipeComponentCallable runnable);

	int getCountOfRunnables();

	JSONObject getNextFinishedComponent();

	void oneLessRunnable();
}
