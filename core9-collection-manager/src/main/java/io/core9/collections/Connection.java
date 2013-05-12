package io.core9.collections;

import org.infinispan.manager.DefaultCacheManager;

public interface Connection {

	void setConnection(DefaultCacheManager cacheManager);
	
	public DefaultCacheManager getCacheManager();

}
