package io.core9.generator;


import io.core9.importers.csv.CsvReader;

import org.junit.Test;

public class ClassGeneratorTest {

	@Test
	public void test() {
		ClassGenerator cg = new ClassGenerator();
		String sourceDir = System.getProperty("user.dir") + "/src/main/java/io/core9/collections/models/impl";
		cg.setsourceDir(sourceDir);
		String packageName = "io.core9.collections.models.impl";
		cg.setPackageName(packageName);
		String[] imports = {"org.hibernate.search.annotations.Field"};
		cg.setImports(imports);
		cg.setName("TestClass");
		
		String[][] fields = CsvReader.getPojoAtributes("Authors.csv");
		//String[][] fields = {{"Field","public","Long","id",""},{"","","","name",""},{"","public","String","surName",""}};
		cg.setFields(fields);
		//String[][] methods = {{"","public","Long","id",""},{"","","","name",""},{"","public","String","surName",""}};
		cg.setMethods(fields);
		
		cg.create();
		

	}

}
