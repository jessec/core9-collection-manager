package io.core9.cdi;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;





public class App  {
    public static void main(String[] args) {
        WeldContainer weld = new Weld().initialize();
        String message = weld.instance().select(HelloWorldWriter.class).get().sayIt();
        System.out.println(message);
    }
}
