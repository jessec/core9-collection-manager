package io.core9.collections.test.integration.java;

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

import io.core9.collections.test.integration.java.fixtures.Addresses;
import io.core9.collections.test.integration.java.handlers.CompleteTest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;




import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.testtools.TestVerticle;

import static org.vertx.testtools.VertxAssert.assertEquals;

/**
 * Example Java integration test
 * 
 * You should extend TestVerticle.
 * 
 * We do a bit of magic and the test will actually be run _inside_ the Vert.x
 * container as a Verticle.
 * 
 * You can use the standard JUnit Assert API in your test by using the
 * VertxAssert class
 */
public class PersistorUpdateTest extends TestVerticle {

	private EventBus eb;
	private List<String> idList = new LinkedList<String>();
	
	private CompleteTest completeTest = new CompleteTest();

	@Override
	public void start() {
		eb = vertx.eventBus();
		JsonObject config = new JsonObject();
		config.putString("address", "io.core9.collections");
		config.putString("db_name", "test_db");
		config.putBoolean("fake", true);
		
		org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	    logger.info("Hello world.");

		container.deployModule(System.getProperty("vertx.modulename"), config,
				1, new Handler<AsyncResult<String>>() {
					@Override
					public void handle(AsyncResult<String> event) {
						System.out.println(event);
					}
				});
		super.start();
	}

	@Ignore("Run after mvn clean install") @Test
	public void testPersistor() throws Exception {
		
		completeTest.completeTest(eb);
		// delete

		JsonObject matcher = new JsonObject().putString("name", "robert");
		JsonObject jsonMatcher = new JsonObject().putString("collection", "io.core9.collections.models.impl.AccountUser")
				.putString("action", "delete")
				.putObject("matcher", matcher);

		eb.send("io.core9.collections", jsonMatcher,
				new Handler<Message<JsonObject>>() {
					public void handle(Message<JsonObject> reply) {
						assertEquals("ok", reply.body().getString("status"));
					}
				});

		// save
		@SuppressWarnings("unused")
		String startupId = io.core9.collections.ObjectId.get().toString();

		long startTime = System.currentTimeMillis();

		final int numDocs = 2;
		for (int i = 0; i < numDocs; i++) {
			System.out.println("nr : " + i);
			String id = io.core9.collections.ObjectId.get().toString();//Integer.toString(i);//io.core9.collections.ObjectId.get().toString(); mongo id's mega slow!!!!!!!!!
			idList.add(id);
			JsonObject doc = new JsonObject()
					.putString("name", "robert")
					.putString("id",
							id)
					.putString("sur name", "cooke");

			JsonObject json = new JsonObject()
					.putString("collection",
							"io.core9.collections.models.impl.AccountUser")
					.putString("action", "save").putObject("document", doc);

			eb.send("io.core9.collections", json,
					new Handler<Message<JsonObject>>() {
						public void handle(Message<JsonObject> reply) {
							//System.out.println(reply.body());
							String res = reply.body().getString("status");
							if (!res.equals("ok")) {
								res = "fail";
							}

							assertEquals("ok", res);
						}
					});
		}

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime)
				+ " milliseconds");


		
		JsonObject matcher2 = new JsonObject().putString("name", "robert");
		JsonObject jsonMatcher2 = new JsonObject()
				.putString("collection",
						"io.core9.collections.models.impl.AccountUser")
				.putString("action", "find").putObject("matcher", matcher2);

		eb.send("io.core9.collections", jsonMatcher2,
				new Handler<Message<JsonObject>>() {
					public void handle(Message<JsonObject> reply) {
						assertEquals("ok", reply.body().getString("status"));
						
						System.out.println(reply.body().toString());
					}
				});


		
		System.out.println("finished..");
		System.out.println(idList);
		
		try {
		    Thread.sleep(10000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		
		for (Iterator<String> iterator = idList.iterator(); iterator.hasNext();) {
			String id = (String) iterator.next();
			System.out.println(id);
			

			JsonObject doc = new JsonObject()
					.putString("name", "robert")
					.putString("id",
							id)
					.putString("sur name", "cooke1");

			JsonObject json = new JsonObject()
					.putString("collection",
							"io.core9.collections.models.impl.AccountUser")
					.putString("action", "save").putObject("document", doc);

			eb.send("io.core9.collections", json,
					new Handler<Message<JsonObject>>() {
						public void handle(Message<JsonObject> reply) {
							//System.out.println(reply.body());
							String res = reply.body().getString("status");
							if (!res.equals("ok")) {
								res = "fail";
							}

							assertEquals("ok", res);
						}
					});
			
		}
		
		
		try {
		    Thread.sleep(10000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		
		JsonObject matcher3 = new JsonObject().putString("name", "robert");
		JsonObject jsonMatcher3 = new JsonObject()
				.putString("collection",
						"io.core9.collections.models.impl.AccountUser")
				.putString("action", "find").putObject("matcher", matcher3);

		eb.send("io.core9.collections", jsonMatcher3,
				new Handler<Message<JsonObject>>() {
					public void handle(Message<JsonObject> reply) {
						assertEquals("ok", reply.body().getString("status"));
						
						System.out.println(reply.body().toString());
					}
				});
		
		
		
		// delete

		JsonObject matcher4 = new JsonObject().putString("name", "robert");
		JsonObject jsonMatcher4 = new JsonObject().putString("collection", "io.core9.collections.models.impl.AccountUser")
				.putString("action", "delete")
				.putObject("matcher", matcher4);

		eb.send("io.core9.collections", jsonMatcher4,
				new Handler<Message<JsonObject>>() {
					public void handle(Message<JsonObject> reply) {
						assertEquals("ok", reply.body().getString("status"));
						
						if ("ok".equals(reply.body().getString("status"))) {
							eb.send(Addresses.TEST_COMPLETE, "ok");
						}
						
					}
				});


		
		
	}

}
