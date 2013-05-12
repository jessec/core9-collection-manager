package io.core9.collections.controllers.impl;




import org.vertx.java.core.json.JsonObject;

import io.core9.collections.controllers.AController;


public class DefaultController extends AController {


	public boolean find() {

		cache = connection.getCacheManager().getCache();

		JsonObject body = message.body();
		@SuppressWarnings("unused")
		JsonObject document = body.getObject("document");
		className = body.getString("collection");
		

		return getIfIdExistsStatus(true, cache);
	}
	


	
}
