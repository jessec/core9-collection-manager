package io.core9.collections.test.integration.java.handlers;

import static org.vertx.testtools.VertxAssert.assertEquals;
import io.core9.collections.test.integration.java.fixtures.Addresses;
import io.core9.collections.test.integration.java.fixtures.Data;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

public class DeleteHandler {

	public void cleanDb(EventBus eb) {
		cleanDb(eb, null);
	}

	public void cleanDb(EventBus eb, JsonObject message) {
		if (message == null) {
			message = Data.getInstance().getAccountUserMatcher("delete");
		}

		eb.send(Addresses.MODULE_ADDRESS, message,
				new Handler<Message<JsonObject>>() {
					public void handle(Message<JsonObject> reply) {
						assertEquals("ok", reply.body().getString("status"));
					}
				});

	}

}
