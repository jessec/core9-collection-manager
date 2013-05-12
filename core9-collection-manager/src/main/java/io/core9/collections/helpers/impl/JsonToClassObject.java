package io.core9.collections.helpers.impl;


import io.core9.generator.ConvertName;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Set;

import org.vertx.java.core.json.JsonObject;






public class JsonToClassObject {

	protected JsonObject jsonObj;
	private Class<?> myClass;


	public void setJsonObj(JsonObject jsonObj) {
		this.jsonObj = jsonObj;
		
		
	}

	
	private Object processJsonObj() {

		String className = jsonObj.getString("collection");
		
		JsonObject doc = jsonObj.getObject("document");
		Object obj = null;

	       try {
	            ClassLoader myClassLoader = ClassLoader.getSystemClassLoader();
	            String classNameToBeLoaded = className;
	            myClass = myClassLoader.loadClass(classNameToBeLoaded);
	            obj = myClass.newInstance();
	        } catch (SecurityException e) {
	            e.printStackTrace();
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (InstantiationException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        } 
	   
		if(doc == null){
			return obj;
		}
        Set<String> keys = doc.getFieldNames();

        for (String s : keys) {
            Method myMethod = null;
			try {
				myMethod = myClass.getMethod(new ConvertName(s).getSetter(), new Class[] { String.class });
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
            try {	
            	myMethod.invoke(obj, new Object[] { doc.getString(s) });
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
        }

		return obj;
	}




	public Object getObject(){
		
		
		return processJsonObj();
	}
	
	
}
