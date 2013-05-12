/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.core9.collections;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.busmods.BusModBase;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import io.core9.collections.controllers.AController;

/**
 * MongoDB Persistor Bus Module
 * <p>
 * Please see the busmods manual for a full description
 * <p>
 * 
 * @author <a href="http://tfox.org">Tim Fox</a>
 * @author Thomas Risberg
 */
public class CollectionManagerPersistor extends BusModBase implements
		Handler<Message<JsonObject>> {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	protected String address;
	protected String host;
	protected int port;
	protected String dbName;
	protected String username;
	protected String password;

	private CollectionManagerConfig config = new CollectionManagerConfig();

	private Connection infinispanConnection = new InfinispanConfig()
			.getConnection();

	private Cache<String, Object> cache = null;
	private String action;

	public void start() {

		super.start();

		address = getOptionalStringConfig("address", "io.core9.collections");
		host = getOptionalStringConfig("host", "localhost");
		port = getOptionalIntConfig("port", 27017);
		dbName = getOptionalStringConfig("db_name", "default_db");
		username = getOptionalStringConfig("username", null);
		password = getOptionalStringConfig("password", null);

		eb.registerHandler("io.core9.collections", this);

	}

	public void stop() {

	}

	public void handle(Message<JsonObject> message) {

		JsonObject body = message.body();

		action = body.getString("action");

		if (action == null) {
			sendError(message, "action must be specified");
			return;
		}

		switch (action) {
		case "save":
			doSave(message);
			break;
		case "update":
			doUpdate(message);
			break;
		case "find":
			doFind(message);
			break;
		case "findone":
			doFindOne(message);
			break;
		case "delete":
			doDelete(message);
			break;
		case "count":
			doCount(message);
			break;
		case "getCollections":
			getCollections(message);
			break;
		case "collectionStats":
			getCollectionStats(message);
			break;
		case "command":
			runCommand(message);
			break;
		default:
			sendError(message, "Invalid action: " + action);
			return;
		}
	}

	private void sendMessage(String action, Message<JsonObject> message) {

		AController controller = config.getController(
				getMandatoryString("collection", message), action);
		if (cache == null) {
			cache = infinispanConnection.getCacheManager().getCache();
		}
		controller.setCache(cache);
		controller.setMessage(message);
		if (controller.run()) {

			JsonObject result = controller.getResult();
			sendOK(message, result);

		} else {
			sendError(message, "error");
		}

	}

	private void doSave(Message<JsonObject> message) {
		sendMessage(action, message);
	}

	private void doUpdate(Message<JsonObject> message) {
	}

	private void doFind(Message<JsonObject> message) {
		sendMessage(action, message);
	}

	private void doFindOne(Message<JsonObject> message) {
		sendMessage(action, message);
	}

	private void doCount(Message<JsonObject> message) {
		sendMessage(action, message);
	}

	private void doDelete(Message<JsonObject> message) {
		sendMessage(action, message);
	}

	private void getCollections(Message<JsonObject> message) {
		sendMessage(action, message);
	}

	private void getCollectionStats(Message<JsonObject> message) {
		sendMessage(action, message);
	}

	private void runCommand(Message<JsonObject> message) {
		sendMessage(action, message);
	}

}
