package io.core9.collections;

import org.infinispan.manager.DefaultCacheManager;

public class InfinispanConnection implements Connection {
	
	private DefaultCacheManager cacheManager;

	@Override
	public void setConnection(DefaultCacheManager cacheManager) {
		this.setCacheManager(cacheManager);
	}

	public DefaultCacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(DefaultCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

}
