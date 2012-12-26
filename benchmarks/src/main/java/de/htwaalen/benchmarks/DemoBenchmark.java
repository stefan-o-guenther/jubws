package de.htwaalen.benchmarks;


import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.MethodRule;



import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

import de.htwaalen.jubws.WebServiceConsumer;


public class DemoBenchmark {
	
	
  @Rule
  public MethodRule benchmarkRun = new BenchmarkRule(new WebServiceConsumer());
 
  
  @Test
  @BenchmarkOptions(callgc = false, benchmarkRounds = 20, warmupRounds = 3)
  public void twentyMillis() throws Exception {
	Thread.sleep(20);
  }
  
  @Test
  @BenchmarkOptions(callgc = false, benchmarkRounds = 20, warmupRounds = 3)
  public void twohundredMillis() throws Exception {
	 Thread.sleep(200);
  }
  
}
