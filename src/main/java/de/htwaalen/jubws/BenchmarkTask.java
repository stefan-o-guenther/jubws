package de.htwaalen.jubws;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.runner.JUnitCore;

/**
 * 
 * This class is used with ExecutorService to execute Benchmarks.
 * 
 */
public class BenchmarkTask implements Callable<BenchmarkResult>{

	/**
	 * Path to a directory or JAR containing the benchmark class.
	 */
	private final String path;
	
	/**
	 * Fully qualified name of the benchmark class.
	 */
	private final String classname;
	
	/**
	 * Variable to store the result of the benchmark.
	 */
	private final BenchmarkResult result;
	
	/**
	 * List of callbacks to notify.
	 */
	private final List<ProgressCallback> progresscallbacks;
	
	/**
	 * Threadlocal variable used to give Consumers in the benchmark a per thread globally available variable to put the results in. 
	 */
	private static final ThreadLocal<BenchmarkTask> localContext = new ThreadLocal<>();
	
	
	/**
	 * Constructs a new BenchmarkTask.
	 * 
	 * @param path Path to a directory or JAR containing the benchmark class.
	 * @param classname Fully qualified name of the benchmark class.
	 * @param progresscallbacks List of callbacks to notify.
	 */
	public BenchmarkTask(String path, String classname, List<ProgressCallback> progresscallbacks) {
		this.classname = classname;
		this.path = path;
		this.result = new BenchmarkResult();
		this.progresscallbacks = progresscallbacks;
	}
	
	@Override
	public BenchmarkResult call() throws MalformedURLException, ClassNotFoundException {
		localContext.set(this);

		URL[] urls = new URL[]{new URL(path)};
		
		result.setDate(Calendar.getInstance());
		result.setClassName(classname);

		
		System.out.println("running bench");
		for(ProgressCallback p : progresscallbacks)
			p.benchmarkStarted(path, classname);

		
		
		try(URLClassLoader classloader = new URLClassLoader(urls, this.getClass().getClassLoader())){
			Class<?> clazz = classloader.loadClass(classname);
			JUnitCore.runClasses(clazz);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			System.out.println("after bench");
			for(ProgressCallback p : progresscallbacks)
				p.benchmarkFinished(getResult());
		}
		

		localContext.remove();
		return getResult();
	}
	

	
	public BenchmarkResult getResult() {
		return result;
	}
	
	public static BenchmarkTask getContext(){
		return localContext.get();
	}
	
	


}
