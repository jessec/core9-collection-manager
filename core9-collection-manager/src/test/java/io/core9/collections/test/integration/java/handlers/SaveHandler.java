package io.core9.collections.test.integration.java.handlers;

import static org.vertx.testtools.VertxAssert.assertEquals;
import io.core9.collections.test.integration.java.Counter;
import io.core9.collections.test.integration.java.fixtures.Addresses;


import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

public class SaveHandler {

	
	public void save(final EventBus eb, final Integer numDocs, JsonObject json, final Boolean closing){
		

		

		eb.send(Addresses.MODULE_ADDRESS, json,
				new Handler<Message<JsonObject>>() {
					public void handle(Message<JsonObject> reply) {

						String res = reply.body().getString("status");
						if (!res.equals("ok")) {
							res = "fail";
						}

						Counter counter = Counter.getInstance();
						counter.inc(this.getClass().getName());
						
						int nr = counter
								.getCount(this.getClass().getName());

						if (nr == numDocs - 1 && res.equals("ok") && closing) {
							eb.send(Addresses.TEST_COMPLETE, "ok");
						}
						
						System.out.println("Counting : ");
						System.out.println(nr);

						assertEquals("ok", res);
					}
				});
	}
	
}
