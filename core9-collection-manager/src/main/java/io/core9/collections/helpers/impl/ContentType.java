package io.core9.collections.helpers.impl;

import java.util.concurrent.Callable;

import com.linkedin.parseq.BaseTask;
import com.linkedin.parseq.Context;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.Tasks;
import com.linkedin.parseq.promise.Promise;

import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;


public class ContentType {

	public static Task<String> getContentType(final String url) {

		return new BaseTask<String>("Get content type: " + url) {
			@Override
			protected Promise<String> run(final Context context)
					throws Exception {
				// We only need to make a HEAD request to get the Content-Type
				// header.
				final Request request = new RequestBuilder().setUrl(url)
						.setMethod("HEAD").build();

				// Create a task to make the HTTP request
				final Task<Response> httpResponse = new HttpRequestTask(request);

				// Create a task to extract the Content-Type header from the
				// response
				final Task<String> contentType = Tasks.callable(
						"Extract content type: " + url, new Callable<String>() {
							@Override
							public String call() throws Exception {

								String contentType = httpResponse.get()
										.getContentType();

								return contentType;
							}
						});

				// Sequence the tasks
				final Task<String> plan = Tasks.seq(httpResponse, contentType);
				context.run(plan);

				return plan;
			}
		};
	}
}