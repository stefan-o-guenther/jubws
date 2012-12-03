package de.htwaalen.jubws;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;

public class TestCases {

	@Test
	public void testBenchmarkResultAdding(){
		int sizeBefore = ListConsumer.getResults().size();
		JUnitCore.runClasses(DemoBenchmark.class); // should add 2 Results
		int sizeAfter = ListConsumer.getResults().size();
				
		assertEquals(sizeBefore + 2, sizeAfter);
	}
	
}
