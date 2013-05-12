package io.core9.collections.importers;

import static org.junit.Assert.*;
import io.core9.generator.ConvertName;

import org.junit.Test;

public class CsvNameToJavaSetterTest {

	private String name;

	// test need to be split up
	@Test
	public void test() {

		ConvertName test = new ConvertName(" name ");
		test.getName();
		
		name = new ConvertName(" name ").getName();
		assertTrue(name.equals("name"));
		
		name = new ConvertName("sur name").getSetter();
		assertTrue(name.equals("setSurName"));
		
		
		name = new ConvertName("sur    name").getSetter();
		assertTrue(name.equals("setSurName"));
		
		
		name = new ConvertName("SUR    name").getName();
		assertTrue(name.equals("surName"));
		

	}

}
