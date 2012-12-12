package de.htwaalen.jubws.server;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.jws.WebService;

import de.htwaalen.jubws.BenchmarkResult;
import de.htwaalen.jubws.BenchmarkTask;

@WebService(endpointInterface = "de.htwaalen.jubws.server.JUnitBenchmarkWebService")
public class JUnitBenchmarkWebServiceImpl implements JUnitBenchmarkWebService {

	private ExecutorService executor;
	private Map<Integer, Future<BenchmarkResult>> tasks;

	public JUnitBenchmarkWebServiceImpl() {
		executor = Executors.newFixedThreadPool(1);
		tasks = new HashMap<>();
	}

	/**
	 * Adds the benchmark request to the execution queue where it will get executed if there is a thread ready for it.
	 * 
	 * @param path Path to a JAR file or directory containing the benchmark class.
	 * @param classname the fully qualified class name of the benchmark.
	 * 
	 * @return A token to query the status and the result of the requested task.
	 */
	@Override
	public int enqueueBenchmark(String path, String classname) {
		System.out.println("called enqueBenchmark(\""+path+"\", \"" + classname + "\")");

		int token = ("path" + "classname" + Calendar.getInstance()).hashCode();

		BenchmarkTask task = new BenchmarkTask(path, classname);
		Future<BenchmarkResult> future = executor.submit(task);
		tasks.put(token, future);

		return token;
	}

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
	 */
	@Override
	public BenchmarkResult getResult(int token) throws ExecutionException, BenchmarkNotDoneException, InvalidTokenException {
		Future<BenchmarkResult> future = tasks.get(token);
		if (future == null) {
			throw new InvalidTokenException();
		} else if (future.isDone()) {			
			BenchmarkResult result = null;
			try {
				result = future.get();
				tasks.remove(token);
			} catch (InterruptedException e) {
				//should never happen since we check isDone.
				e.printStackTrace();
			}
			return result;
		}else{
			throw new BenchmarkNotDoneException();
		}
	}

	/**
	 * Queries the status of the request.
	 * 
	 * @param token The token to identify the request.
	 * 
	 * @return A boolean indicating if the benchmark is done.
	 * 
	 * @exception InvalidTokenException if the token doesn't refer to a request.
	 */
	@Override
	public boolean isDone(int token) throws InvalidTokenException {
		//System.out.println("called isDone("+token+")");
		Future<BenchmarkResult> future = tasks.get(token);
		if (future == null) {
			throw new InvalidTokenException();
		} else {
			return future.isDone();
		}
	}

}
