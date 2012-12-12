package de.htwaalen.jubws;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class BenchmarkTask implements Callable<BenchmarkResult>{

	private final String path;
	private final String classname;
	private final BenchmarkResult result;
	
	private static final ThreadLocal<BenchmarkTask> localContext = new ThreadLocal<>();
	
	public BenchmarkTask(String path, String classname) {
		this.classname = classname;
		this.path = path;
		this.result = new BenchmarkResult();
	}
	
	@Override
	public BenchmarkResult call() throws MalformedURLException, ClassNotFoundException {
		localContext.set(this);

		URL[] urls = new URL[]{new URL(path)};
		
		System.out.println("running bench");
		
		
		try(URLClassLoader classloader = new URLClassLoader(urls, this.getClass().getClassLoader())){
			Class<?> clazz = classloader.loadClass(classname);
			JUnitCore.runClasses(clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("after bench");
		
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
