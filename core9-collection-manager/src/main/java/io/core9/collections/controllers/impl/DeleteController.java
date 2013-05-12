package io.core9.collections.controllers.impl;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.infinispan.query.CacheQuery;
import org.vertx.java.core.json.JsonObject;



import io.core9.collections.controllers.AController;

public class DeleteController extends AController {



	
	public boolean run() {
		
		
		JsonObject body = message.body();
		className = body.getString("collection");
		JsonObject matcher = body.getObject("matcher");

		Class<?> myClass = getClazz();
		CacheQuery query = getFullQuery(myClass, matcher);
		List<Object> resultList = query.list();
		
		for (Object obj : resultList) {
			
            Method myMethod = null;
			try {
				myMethod = myClass.getMethod("getId");
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
            try {	
            	id = (String) myMethod.invoke(obj);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			cache.remove(id, obj);
		}
		

		List<Object> deleteList = query.list();
		if(deleteList.isEmpty()){
			return true;
		}
		return false;
	}


}
