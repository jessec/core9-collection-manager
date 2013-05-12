package io.core9.collections.router;

import io.core9.collections.Uri;

/**
 * @author jesse
 * @version 1.0
 * @created 02-Mar-2013 10:24:40 PM
 */
public interface Router {

	/**
	 * 
	 * @param uri
	 */
	public void setRouterRequest(Uri uri);

	public RouterResponse getRouterResponse();

}