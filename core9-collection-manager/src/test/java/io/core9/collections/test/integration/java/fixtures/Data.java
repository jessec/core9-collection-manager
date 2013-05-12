package io.core9.collections.test.integration.java.fixtures;

import org.vertx.java.core.json.JsonObject;

public class Data {

	private static Data instance;

	private Data() {
	}

	public static Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}

	public JsonObject getAccountUserMatcher(String action) {

		return new JsonObject()
				.putString("collection",
						"io.core9.collections.models.impl.AccountUser")
				.putString("action", action)
				.putObject("matcher",
						new JsonObject().putString("name", "robert"));
	}

	public JsonObject getAccountUser(String id) {

		JsonObject doc = new JsonObject()
		.putString("name", "robert")
		.putString("id", id
				)
		.putString("sur name", "cooke");

JsonObject json = new JsonObject()
		.putString("collection",
				"io.core9.collections.models.impl.AccountUser")
		.putString("action", "save").putObject("document", doc);

		return json;
	}

}
