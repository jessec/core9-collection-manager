package io.core9.collections.test.integration.java.providers;

import io.core9.collections.test.integration.java.handlers.DeleteHandler;

import java.io.IOException;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class CleanerFactory {

	public @Produces
	@Cleaner
	DeleteHandler getConfiguration(InjectionPoint ip) throws IOException {

		return new DeleteHandler();
	}
}
