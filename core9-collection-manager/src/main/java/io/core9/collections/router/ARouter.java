package io.core9.collections.router;

import io.core9.collections.controllers.AControllerRequest;
import io.core9.collections.controllers.AControllerResponse;
import io.core9.collections.views.AViewRequest;
import io.core9.collections.views.AViewResponse;

/**
 * @author jesse
 * @version 1.0
 * @created 02-Mar-2013 10:24:40 PM
 */
abstract class ARouter {

	public static ARouterRequest request;
	public static ARouterResponse response;

	public ARouter(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	/**
	 * 
	 * @param request    request
	 */
	public ARouterResponse getResponse(ARouterRequest request){
		return null;
	}

	/**
	 * 
	 * @param controllerRequest    controllerRequest
	 */
	public AControllerResponse getControllerResponse(AControllerRequest controllerRequest){
		return null;
	}

	/**
	 * 
	 * @param viewRequest    viewRequest
	 */
	public AViewResponse getView(AViewRequest viewRequest){
		return null;
	}

}