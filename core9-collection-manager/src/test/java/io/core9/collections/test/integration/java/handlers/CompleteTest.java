package io.core9.collections.test.integration.java.handlers;

import static org.vertx.testtools.VertxAssert.testComplete;

import javax.inject.Inject;

import io.core9.collections.test.integration.java.fixtures.Addresses;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;

public class CompleteTest {
	
	
	private DeleteHandler cleanDb = new DeleteHandler();

	public void completeTest(final EventBus eb) {

		Handler<Message<String>> handler = new Handler<Message<String>>() {
			public void handle(Message<String> message) {
				String body = message.body();
				if (body.equals("ok")) {
					cleanDb.cleanDb(eb);
					testComplete();
				}
			}
		};

		eb.registerHandler(Addresses.TEST_COMPLETE, handler);
	}

}
