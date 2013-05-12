package io.core9.cdi;

import static org.junit.Assert.*;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

public class TestCdi {

	@Test
	public void test() {
        WeldContainer weld = new Weld().initialize();
        String message = weld.instance().select(HelloWorldWriter.class).get().sayIt();
       
        assertEquals("Hello World!", message);
	}

}
