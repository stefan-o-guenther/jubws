package de.htwaalen.benchmarks;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;



import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

import de.htwaalen.jubws.WebServiceConsumer;

public class FileAccessBenchmark {

	@Rule
	  public MethodRule benchmarkRun = new BenchmarkRule(new WebServiceConsumer());
	 
	  
	  @Test
	  @BenchmarkOptions(callgc = false, benchmarkRounds = 20, warmupRounds = 3)
	  public void resourceAccessTest() throws Exception {
		InputStream in = getClass().getResourceAsStream("/resources/helloworld");
		Scanner scanner = new Scanner(in).useDelimiter("\\z");
		String content = scanner.hasNext()?scanner.next():"";
		System.out.println("File content: >"+content+"<");
	  }
	  
	
}
