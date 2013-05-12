package io.core9.collections;

/*
 * Copyright 2013 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */

import io.core9.collections.controllers.impl.Index;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

public class MainVerticle extends Verticle {

	public void start() {

		
		
		EventBus eb = vertx.eventBus();
		Handler<Message<String>> myHandler = new Handler<Message<String>>() {
			public void handle(Message<String> message) {

				JsonObject config = container.config();
				Index index = new Index();
				index.runParallelTasks(message, config);

			}
		};
		eb.registerHandler("test.address", myHandler);

	}

	public void stop() {
	}
}
