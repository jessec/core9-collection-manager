package io.core9.collections.controllers.impl;

import java.util.List;
import org.infinispan.query.CacheQuery;

import org.vertx.java.core.json.JsonObject;


import io.core9.collections.controllers.AController;


public class FindController extends AController {

	private JsonObject result;

	public boolean run() {
		long startTime = System.currentTimeMillis();

		JsonObject body = message.body();

		className = body.getString("collection");
		JsonObject matcher = body.getObject("matcher");

		Class<?> myClass = getClazz();

		CacheQuery query = getFullQuery(myClass, matcher);

		List<Object> resultList = query.list();

		result = getJsonObjFromList(resultList);

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime)
				+ " milliseconds");
		return getStatus(resultList);
	}
	
	public JsonObject getResult() {
		return result;
	}

}
