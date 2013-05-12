package io.core9.collections.models.impl;

import io.core9.collections.Connection;
import io.core9.collections.controllers.AController;
import io.core9.collections.models.User;
import io.core9.collections.models.Users;


import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.infinispan.Cache;

import org.infinispan.query.CacheQuery;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;


import org.junit.Test;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

public class BaseProducts extends AController implements Users {

	private Connection connection;
	
	private JsonObject reply;

	@SuppressWarnings("unused")
	private Message<JsonObject> message;
	
	public void addUser(String id, String name, String surname){
		
		Cache<String, AccountUser> cache = connection.getCacheManager()
				.getCache();

		AccountUser accountUser = new AccountUser(id, name, surname);
		cache.put(accountUser.getId(), accountUser);
		
	}
	
	public boolean save(){
		
		
		return false;
		
	}

	@Test
	public boolean find() {

		// specific
		// insert

		Cache<String, AccountUser> cache = connection.getCacheManager()
				.getCache();
		SearchManager sm = Search.getSearchManager(cache);

		AccountUser accountUser = new AccountUser("1", "Manik dfdafs: + :ge_wicht_7: ", "Surtani");
		cache.put(accountUser.getId(), accountUser);

		AccountUser accountUser2 = new AccountUser("2", "manik :afdasdf:dsafdsa    :fsdaf: + :ge_wicht_9: ", "Punnie");
		cache.put(accountUser2.getId(), accountUser2);

		// specific
		// select
		final long startTime = System.nanoTime();
		QueryBuilder qb = sm.buildQueryBuilderForClass(AccountUser.class).get();
		Query q = qb.keyword().onField("name").matching(":ge_wicht_9:").createQuery();
		CacheQuery cq = sm.getQuery(q, AccountUser.class);

		//Assert.assertEquals(2, cq.getResultSize());
		int result = cq.getResultSize();
		
		
		final long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		double seconds = (double)elapsedTime / 1000000000.0;
		System.out.println("which is " + seconds + " seconds"); 
		
		if (result > 0) {
			
			List<?> objectList = cq.list();
			
			
			reply = new JsonObject().putString("collection", "testcoll").putString("action", "save");
			 
			for (Object book : objectList) {
				
				AccountUser user = (AccountUser) book; 
			      System.out.println(user.name);
			      
			      JsonObject doc = new JsonObject().putString("name", user.name)
							.putNumber("age", 40).putString("surname", user.surname);

					
							reply.putObject("document"+user.hashCode(), doc);
			      			
			}
			

		

			return true;
		}

		return false;

	}

	public JsonObject getResult() {

		return reply;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void setMessage(Message<JsonObject> message){
		this.message = message;
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
