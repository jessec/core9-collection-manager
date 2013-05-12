package io.core9.generator;



import org.junit.Test;

public class ClassFieldsTest {

	@Test
	public void test() {
		
		ClassFields fieldGenerator = new ClassFields();
		
		String[][] fields = {{"inject","private","Long","id"},{"","","","name"},{"inject","private","String","surName"}};
		fieldGenerator.setFields(fields);
		
		String result = fieldGenerator.getFields();
		
		System.out.println(result);
		
	}

}
