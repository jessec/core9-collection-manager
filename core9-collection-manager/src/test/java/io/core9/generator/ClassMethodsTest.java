package io.core9.generator;


import org.junit.Test;

public class ClassMethodsTest {

	@Test
	public void test() {
		ClassMethods methodGenerator = new ClassMethods();
		
		String[][] methods = {{"inject","private","Long","id", ""},{"","","","name",""},{"inject","private","String","surName","g"}};
		methodGenerator.setMethods(methods);
		
		String result = methodGenerator.getMethods();
		
		System.out.println(result);
	}

}
