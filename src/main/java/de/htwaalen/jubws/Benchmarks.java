package de.htwaalen.jubws;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Benchmarks {

	private static List<BenchmarkResult> results = Collections.synchronizedList(new LinkedList<BenchmarkResult>());
	
	public static void addResult(BenchmarkResult result){
		results.add(result);
	}
	
	
	public static List<BenchmarkResult> getResults(){
		return results;
	}
	
}
