package io.core9.collections.controllers;

import io.core9.collections.Connection;

/**
 * @author jesse
 * @version 1.0
 * @created 02-Mar-2013 10:24:40 PM
 */
public interface Controller {

	/**
	 * 
	 * @param controllerRequest
	 */
	void setControllerRequest(AControllerRequest controllerRequest);

	AControllerResponse getControllerResponse();

	boolean execute();

	void setConnection(Connection connection);

}