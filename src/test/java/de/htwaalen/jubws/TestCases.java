package de.htwaalen.jubws;

import static junit.framework.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import de.htwaalen.jubws.server.JUnitService;
import de.htwaalen.jubws.server.JUnitServiceImpl;

public class TestCases {

	@Test
	public void testBenchmarkResultAdding(){
		int sizeBefore = ListConsumer.getResults().size();
		JUnitCore.runClasses(DemoBenchmark.class); // should add 2 Results
		int sizeAfter = ListConsumer.getResults().size();
				
		assertEquals(sizeBefore + 2, sizeAfter);
	}
	
	@Test(expected=MalformedURLException.class)
	public void testInvalidPath() throws MalformedURLException, ClassNotFoundException, IOException{
		JUnitService junit = new JUnitServiceImpl();
		junit.runBenchmark("", "");
	}
	
}
