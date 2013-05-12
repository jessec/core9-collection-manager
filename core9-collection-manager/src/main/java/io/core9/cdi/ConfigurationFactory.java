package io.core9.cdi;


import java.io.IOException;
import java.util.Properties;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class ConfigurationFactory {
    
    public @Produces @Configuration String getConfiguration(InjectionPoint ip) throws IOException {
        Properties props = new Properties();
        props.load(ConfigurationFactory.class.getResourceAsStream("/messages.properties"));
        return props.getProperty(ip.getMember().getDeclaringClass().getSimpleName() + "." + ip.getMember().getName());
    }
}
