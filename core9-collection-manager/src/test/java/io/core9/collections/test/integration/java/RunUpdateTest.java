package io.core9.collections.test.integration.java;



import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Ignore;
import org.junit.Test;

import org.vertx.testtools.*;

public class RunUpdateTest extends TestVerticle {

	public void start() {
		super.start();
	}

	@Ignore("Run after mvn clean install") @Test
	public void saveTest() throws Exception {

		 WeldContainer weld = new Weld().initialize();
	        weld.instance().select(FindTest.class).get().run(vertx, container);
	}
	


}