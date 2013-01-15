package de.htwaalen.jubws;


import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Test;

import de.htwaalen.jubws.JUnitBenchmarkService.BenchmarkNotDoneException;
import de.htwaalen.jubws.JUnitBenchmarkService.InvalidTokenException;

public class TestCases {


	
	@Test(expected=MalformedURLException.class)
	public void testInvalidPath() throws Throwable {
		JUnitBenchmarkService junit = new JUnitBenchmarkServiceImpl();
		int token = junit.enqueueBenchmark("", "");
		while(!junit.isDone(token));
		try {
			junit.getResult(token);
		} catch (ExecutionException e) {
			throw e.getCause();
		}
	}
	
	@Test(expected=ClassNotFoundException.class)
	public void testInvalidClass() throws Throwable {
		JUnitBenchmarkService junit = new JUnitBenchmarkServiceImpl();
		int token = junit.enqueueBenchmark("file:", "");
		while(!junit.isDone(token));
		try {
			junit.getResult(token);
		} catch (ExecutionException e) {
			throw e.getCause();
		}
	}
	

	@Test
	public void testGetResults() throws BenchmarkNotDoneException, InvalidTokenException, ExecutionException {
		JUnitBenchmarkService junit = new JUnitBenchmarkServiceImpl();
		int token = junit.enqueueBenchmark("file:benchmarks/target/benchmarks-1.0.0.jar", "de.htwaalen.benchmarks.DemoBenchmark");
		while(!junit.isDone(token));
		BenchmarkResult result = junit.getResult(token);
		Assert.assertEquals(2, result.getMethods().size());
	}
	
}
