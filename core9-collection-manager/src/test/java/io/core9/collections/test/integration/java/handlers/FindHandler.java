package io.core9.collections.test.integration.java.handlers;

import static org.vertx.testtools.VertxAssert.assertEquals;
import io.core9.collections.test.integration.java.fixtures.Addresses;
import io.core9.collections.test.integration.java.fixtures.Data;


import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

public class FindHandler {

	
	public void find(final EventBus eb, final Integer numDocs, final Boolean closing){
		

		eb.send("io.core9.collections", Data.getInstance().getAccountUserMatcher("find"),
				new Handler<Message<JsonObject>>() {
					public void handle(Message<JsonObject> reply) {
						assertEquals("ok", reply.body().getString("status"));
						
						
						if (reply.body().size() == numDocs + 1 && reply.body().getString("status").equals("ok")) {
							eb.send(Addresses.TEST_COMPLETE, "ok");
						}
						
						
						System.out.println(reply.body().toString());
					}
				});
		
	}
	
}
