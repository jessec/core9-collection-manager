package io.core9.collections.controllers;


import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

/**
 * @author jesse
 * @version 1.0
 * @created 02-Mar-2013 10:24:40 PM
 */
public abstract class AControllerRequest {
	
	private Message<JsonObject> message;

	public AControllerRequest(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	public Message<JsonObject> getMessage() {
		return message;
	}

	public void setMessage(Message<JsonObject> message2) {
		this.message = message2;
	}

}