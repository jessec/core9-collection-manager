package io.core9.collections.controllers.impl;
//http://docs.jboss.org/hibernate/stable/search/reference/en-US/html_single/#section-building-lucene-queries
//5.1.2.6. Combining queries


import io.core9.collections.controllers.AController;

public class UpdateController extends AController {

	public boolean run() {
		
		Object classObj = prepareMessageForSave();
		cache.put(id, classObj);
		
		return getIfIdExistsStatus(true, cache);
	}



}
