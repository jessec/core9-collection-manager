package io.core9.collections;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.core9.collections.controllers.AController;
import io.core9.collections.controllers.impl.DefaultController;
import io.core9.collections.controllers.impl.DeleteController;
import io.core9.collections.controllers.impl.FindController;
import io.core9.collections.controllers.impl.SaveController;
import io.core9.collections.controllers.impl.UpdateController;

public class CollectionManagerConfig {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private HashMap<String, HashMap<String, AController>> hm = new HashMap<String, HashMap<String, AController>>();
	private String singletonFlag = null;

	private void addMappings() {
		addMapping("io.core9.collections.models.impl.AccountUser", "find",
				new FindController());
		addMapping("io.core9.collections.models.impl.AccountUser", "delete",
				new DeleteController());
		addMapping("io.core9.collections.models.impl.AccountUser", "update",
				new UpdateController());
		addMapping("io.core9.collections.models.impl.AccountUser", "save",
				new SaveController());
	}

	public void addMapping(String collection, String action,
			AController controller) {
		
		HashMap<String, AController> map = new HashMap<String, AController>();
		map.put(action, controller);
		hm.put(getKey(collection, action), map);
		logger.info("Collection : " + collection + " and action : " + action
				+ " useing Controller : "
				+ controller.getClass().getCanonicalName());
	}

	public AController getController(String collection, String action) {

		if (singletonFlag == null) {
			addMappings();
			singletonFlag = "loaded";
		}

		AController controller = new DefaultController();
		try {
			controller = hm.get(getKey(collection, action)).get(action);
		} catch (Exception e) {
			System.out.println("Collection : " + collection + " and action : "
					+ action + " where not found");
			logger.error("Collection : " + collection + " and action : "
					+ action + " useing Controller : "
					+ controller.getClass().getCanonicalName()
					+ " exception : " + e.getMessage());
		}

		return controller;
	}

	private String getKey(String collection, String action) {

		return collection + "." + action;
	}

}