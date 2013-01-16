package de.htwaalen.jubws;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;




public class JUnitBenchmarkServiceImpl implements JUnitBenchmarkService{

	private ExecutorService executor;
	private Map<Integer, Future<BenchmarkResult>> tasks;
	private List<ProgressCallback> callbacks;

	public JUnitBenchmarkServiceImpl() {
		executor = Executors.newFixedThreadPool(2);
		tasks = new HashMap<>();
		callbacks = new ArrayList<>();
	}


	@Override
	public int enqueueBenchmark(String path, String classname) {
		System.out.println("called enqueBenchmark(\""+path+"\", \"" + classname + "\")");
		

		int token = ("path" + "classname" + Calendar.getInstance()).hashCode();
		System.out.println("Generated token: "+token);

		BenchmarkTask task = new BenchmarkTask(path, classname, callbacks);
		Future<BenchmarkResult> future = executor.submit(task);
		tasks.put(token, future);

		return token;
	}


	@Override
	public BenchmarkResult getResult(int token) throws ExecutionException, BenchmarkNotDoneException, InvalidTokenException {
		System.out.println("getResult requested token: "+token);
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


	@Override
	public boolean isDone(int token) throws InvalidTokenException {
		System.out.println("isDone requested token: "+token);
		Future<BenchmarkResult> future = tasks.get(token);
		if (future == null) {
			throw new InvalidTokenException();
		} else {
			boolean result = future.isDone();
			System.out.println("isDone for token "+token+" is "+result);
			return result;
		}
	}
	
	public List<ProgressCallback> getCallbacks() {
		return callbacks;
	}
	
	public void setCallbacks(List<ProgressCallback> callbacks) {
		this.callbacks = callbacks;
	}

	
}
