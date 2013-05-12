package io.core9.collections.importers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.core9.importers.csv.CsvReader;

import org.junit.Test;

public class ReaderTest {

	//@Test
	@SuppressWarnings("unused")
	public void test() {
		
		List<Map<String, String>> reader = null;
		try {
			reader = CsvReader.read("Authors.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(reader.size() == 3);
		
		// Iterate
		for (Map<String, String> rowMap : reader) {
			//System.out.println(rowMap);
			for (Entry<String, String> entry : rowMap.entrySet()) {
			/*    String key = entry.getKey();
			    String value = entry.getValue();
			    System.out.println(key);
			    System.out.println(value);
			    System.out.println("\n\r");
			*/}
			//System.out.println("\n\r");
		}
	}
	
	
	@SuppressWarnings("unused")
	@Test
	public void testGetHeaders() {
		
		String[][] headers = CsvReader.getPojoAtributes("Authors.csv");
	}

}
