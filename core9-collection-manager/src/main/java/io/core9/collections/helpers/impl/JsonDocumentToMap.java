package io.core9.collections.helpers.impl;

import io.core9.generator.ConvertName;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.vertx.java.core.json.JsonObject;

public class JsonDocumentToMap {

	
	public static Map<String, String> convert(JsonObject document) {

		
		Map<String, String> map = new HashMap<>();
		if(document == null){
			return map;
		}
		
		

		Set<String> array = document.getFieldNames();

		for (String field : array) {
			String fld = new ConvertName(field).getName();
			System.out.println(fld + " : " + document.getString(field));
			map.put(fld, document.getString(field));
		}

		return map;

	}
	
}
