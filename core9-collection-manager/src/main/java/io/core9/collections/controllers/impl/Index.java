package io.core9.collections.controllers.impl;

import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.ParTask;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.Tasks;
import com.linkedin.parseq.promise.Promise;
import com.linkedin.parseq.promise.PromiseListener;

import io.core9.collections.helpers.impl.ContentType;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

public class Index {

	final int numCores = Runtime.getRuntime().availableProcessors();
	final ExecutorService taskScheduler = Executors
			.newFixedThreadPool(numCores + 1);
	final ScheduledExecutorService timerScheduler = Executors
			.newSingleThreadScheduledExecutor();

	final Engine engine = new EngineBuilder().setTaskExecutor(taskScheduler)
			.setTimerScheduler(timerScheduler).build();

	public void runParallelTasks(final Message<String> message, JsonObject config) {

		final Task<String> googleContentType = ContentType
				.getContentType("http://www.google.com");
		final Task<String> bingContentType = ContentType
				.getContentType("http://www.bing.com");
		final Task<String> yahooContentType = ContentType
				.getContentType("http://www.yahoo.com");
		final Task<List<String>> fetchContentTypes = Tasks.par(
				googleContentType, bingContentType, yahooContentType);

		fetchContentTypes.addListener(new PromiseListener<List<String>>() {

			@Override
			public void onResolved(Promise<List<String>> promise) {

				final List<String> successful = ((ParTask<String>) fetchContentTypes)
						.getSuccessful();


				message.reply("Fully async and parallel response : " + successful.toString());
			}
		});
		engine.run(fetchContentTypes);


	}

}
