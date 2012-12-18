package de.htwaalen.jubws;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface JUnitBenchmarkService {
	public class BenchmarkNotDoneException extends RuntimeException{
	}
	public class InvalidTokenException extends RuntimeException{
	}
	
	
	/**
	 * Adds the benchmark request to the execution queue where it will get executed if there is a thread ready for it.
	 * 
	 * @param path Path to a JAR file or directory containing the benchmark class.
	 * @param classname the fully qualified class name of the benchmark.
	 * 
	 * @return A token to query the status and the result of the requested task.
	 * 
	 */
    int enqueueBenchmark(String path, String classname);
    
    
	/**
	 * Queries the status of the request.
	 * 
	 * @param token The token to identify the request.
	 * 
	 * @return A boolean indicating if the benchmark is done.
	 * 
	 * @exception InvalidTokenException if the token doesn't refer to a request.
	 * 
	 */
    boolean isDone(int token) throws InvalidTokenException;
    
	/**
	 * Retrieves the results for a request.
	 * 
	 * @param token The token to identify the request.
	 * 
	 * @return Returns the result of the requested benchmark.
	 * 
	 * @exception ExecutionException if the Benchmark threw a exception.
	 * @exception InvalidTokenException if the token doesn't refer to a request.
	 * @exception BenchmarkNotDoneException if the benchmark is not yet done.
	 * 
	 */
    BenchmarkResult getResult(int token) throws ExecutionException, BenchmarkNotDoneException, InvalidTokenException ;

	public List<ProgressCallback> getCallbacks();
	
	public void setCallbacks(List<ProgressCallback> callbacks);
}
