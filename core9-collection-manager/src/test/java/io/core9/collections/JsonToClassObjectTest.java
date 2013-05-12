package io.core9.collections;

import static org.junit.Assert.*;


import org.junit.Test;
import org.vertx.java.core.json.JsonObject;

import io.core9.collections.helpers.impl.*;


public class JsonToClassObjectTest {

	@Test
	public void test() {
		

		
		JsonToClassObject obj = new JsonToClassObject();
		
		

		String className = "io.core9.collections.models.impl.Author";
		
		JsonObject doc = new JsonObject().putString("name", "robert")
				.putString("id", "id").putString("sur name", "cooke");
		JsonObject json = new JsonObject()
				.putString("collection", className)
				.putString("action", "save").putObject("document", doc);
		

		obj.setJsonObj(json);
		
		Object classObj = obj.getObject();
		
		if ( classObj.getClass().getName().equals(className)) {
		    assertTrue(true);
		}else{
			assertFalse(false);
		}
		
		
	}


}
