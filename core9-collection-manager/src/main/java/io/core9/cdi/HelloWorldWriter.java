package io.core9.cdi;


import javax.inject.Inject;

public class HelloWorldWriter {
    @Inject @Configuration
    private String message;
    
    public String sayIt() {
        //System.out.println(message);
    	return message;
    }
}
