package io.core9.collections;

import io.core9.collections.models.impl.AccountUser;

import java.lang.annotation.ElementType;
import java.util.Properties;

import org.hibernate.search.cfg.SearchMapping;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;

import org.infinispan.manager.DefaultCacheManager;

public class InfinispanConfig implements Connection {

	private DefaultCacheManager cacheManager = null;
	
	public Connection setup(){
		

		 
		
		if(cacheManager == null){
			
			SearchMapping mapping = new SearchMapping();
			mapping.entity(AccountUser.class).indexed().providedId()
			      .property("name", ElementType.METHOD).field()
			      .property("surname", ElementType.METHOD).field();
			 
			Properties properties = new Properties();
			properties.put(org.hibernate.search.Environment.MODEL_MAPPING, mapping);
			//properties.put("hibernate.search.[other options]", "[...]");
			 
			Configuration infinispanConfiguration = new ConfigurationBuilder()
			      .indexing()
			         .enable()
			         .indexLocalOnly(true)
			         .withProperties(properties)
			      .build();
			
			cacheManager = new DefaultCacheManager(infinispanConfiguration);
		}
		

		
		Connection connection = new InfinispanConnection();
		connection.setConnection(cacheManager);
		
		return connection;
		
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return setup();
	}

	@Override
	public void setConnection(DefaultCacheManager cacheManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DefaultCacheManager getCacheManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
