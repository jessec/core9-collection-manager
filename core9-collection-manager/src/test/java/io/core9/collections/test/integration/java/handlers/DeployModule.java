package io.core9.collections.test.integration.java.handlers;

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Container;

public class DeployModule {

	
	public void deploy(Container container){
		
		JsonObject config = new JsonObject();
		config.putString("address", "io.core9.collections");
		config.putString("db_name", "test_db");
		config.putBoolean("fake", true);

		// embeded infinispan can only work with 1 instance
		container.deployModule(System.getProperty("vertx.modulename"), config,
				1, new Handler<AsyncResult<String>>() {
					@Override
					public void handle(AsyncResult<String> event) {
					}
				});
	}
	
}
