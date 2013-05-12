package io.core9.collections.test.integration.java;



import javax.inject.Inject;

import io.core9.collections.ObjectId;
import io.core9.collections.test.integration.java.fixtures.Data;
import io.core9.collections.test.integration.java.handlers.DeleteHandler;
import io.core9.collections.test.integration.java.handlers.CompleteTest;
import io.core9.collections.test.integration.java.handlers.DeployModule;
import io.core9.collections.test.integration.java.handlers.FindHandler;
import io.core9.collections.test.integration.java.handlers.SaveHandler;
import io.core9.collections.test.integration.java.providers.Cleaner;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.platform.Container;

public class FindTest {

	private EventBus eb;
	private int numDocs;

	@Inject
	@Cleaner
	private DeleteHandler cleanDb;
	@Inject
	private CompleteTest completeTest;
	@Inject
	private SaveHandler saveHandler;
	@Inject
	private DeployModule deployModule;
	@Inject
	private FindHandler findHandler;
	

	public void save(Vertx vertx) throws Exception {

		String id = ObjectId.get().toString();
		numDocs = 8;
		for (int i = 0; i < numDocs; i++) {
			System.out.println(i);
			id = ObjectId.get().toString();
			saveHandler.save(eb, numDocs, Data.getInstance().getAccountUser(id), false);
		}
	}

	private void find(Vertx vertx) {

		findHandler.find(eb, numDocs, true);
		
	}
	
	public void run(Vertx vertx, Container container) {

		eb = vertx.eventBus();
		deployModule.deploy(container);
		
		cleanDb.cleanDb(eb, Data.getInstance().getAccountUserMatcher("delete"));
		completeTest.completeTest(eb);
		
		try {
			save(vertx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			find(vertx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
