package de.htwaalen.jubws;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.runner.JUnitCore;


public class BenchmarkTask implements Callable<BenchmarkResult>{

	private final String path;
	private final String classname;
	private final BenchmarkResult result;
	private final List<ProgressCallback> progresscallbacks;
	
	private static final ThreadLocal<BenchmarkTask> localContext = new ThreadLocal<>();
	
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
