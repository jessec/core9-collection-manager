package io.core9.collections.models;

/**
 * @author jesse
 * @version 1.0
 * @created 02-Mar-2013 10:24:40 PM
 */
public interface Model {

	/**
	 * 
	 * @param modelRequest
	 */
	public void setModelRequest(ModelRequest modelRequest);

	public ModelResponse getModelResponse();

}