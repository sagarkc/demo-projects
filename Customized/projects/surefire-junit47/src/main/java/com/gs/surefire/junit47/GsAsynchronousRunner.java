package com.gs.surefire.junit47;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.maven.surefire.util.NestedRuntimeException;
import org.junit.runners.model.RunnerScheduler;

/**
 * @author <a href="mailto:sabuj.das@gmail.com">Sabuj Das</a>
 */

public class GsAsynchronousRunner implements RunnerScheduler {
	private final List<Future<Object>> futures = new ArrayList<Future<Object>>();

	private final ExecutorService fService;

	public GsAsynchronousRunner(ExecutorService fService) {
		this.fService = fService;
	}

	public void schedule(final Runnable childStatement) {
		futures.add(fService.submit(new Callable<Object>() {
			public Object call() throws Exception {
				childStatement.run();
				return null;
			}
		}));
	}

	public void finished() {
		try {
			waitForCompletion();
		} catch (ExecutionException e) {
			throw new NestedRuntimeException(e);
		}
	}

	public void waitForCompletion() throws ExecutionException {
		for (Future<Object> each : futures) {
			try {
				each.get();
			} catch (InterruptedException e) {
				throw new NestedRuntimeException(e);
			}
		}
	}
}
