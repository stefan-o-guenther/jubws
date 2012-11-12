package de.htwaalen.jubws;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;

public class TestCases {

	@Test
	public void testBenchmarkResultAdding(){
		int sizeBefore = Benchmarks.getResults().size();
		JUnitCore.runClasses(DemoBenchmark.class); // should add 2 Results
		int sizeAfter = Benchmarks.getResults().size();
				
		assertEquals(sizeBefore + 2, sizeAfter);
	}
	
}
