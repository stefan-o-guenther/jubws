package de.htwaalen.jubws;


import java.net.MalformedURLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Test;

import de.htwaalen.jubws.server.JUnitBenchmarkWebService;
import de.htwaalen.jubws.server.JUnitBenchmarkWebServiceImpl;

public class TestCases {


	
	@Test(expected=MalformedURLException.class)
	public void testInvalidPath() throws Throwable {
		JUnitBenchmarkWebService junit = new JUnitBenchmarkWebServiceImpl();
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
		JUnitBenchmarkWebService junit = new JUnitBenchmarkWebServiceImpl();
		int token = junit.enqueueBenchmark("file:///", "");
		while(!junit.isDone(token));
		try {
			junit.getResult(token);
		} catch (ExecutionException e) {
			throw e.getCause();
		}
	}
	

	@Test
	public void testGetResults() throws ExecutionException, InterruptedException, NoSuchElementException {
		JUnitBenchmarkWebService junit = new JUnitBenchmarkWebServiceImpl();
		int token = junit.enqueueBenchmark("file:///home/lucid/remote/stash/Dropbox/htw-aalen/Distributed Applications/Project/benchmarks/target/benchmarks-1.0.0.jar", "de.htwaalen.benchmarks.DemoBenchmark");
		while(!junit.isDone(token));
		BenchmarkResult result = junit.getResult(token);
		Assert.assertEquals(2, result.getMethods().size());
	}
	
}
