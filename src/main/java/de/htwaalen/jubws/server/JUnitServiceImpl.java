package de.htwaalen.jubws.server;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import javax.jws.WebService;

import org.junit.runner.JUnitCore;


import de.htwaalen.jubws.BenchmarkResult;
import de.htwaalen.jubws.ListConsumer;

@WebService(endpointInterface = "de.htwaalen.jubws.server.JUnitService")
public class JUnitServiceImpl implements JUnitService {

	
	public JUnitServiceImpl() {
	}

	@Override
	public void runBenchmark(String path, String classname) throws ClassNotFoundException, IOException {
		System.out.println("called run(\"" + classname + "\")");
		//TODO improve error handling
		try(URLClassLoader classloader = new URLClassLoader(new URL[]{new URL(path)})){
			JUnitCore.runClasses(classloader.loadClass(classname));
		}
	}

	@Override
	public List<BenchmarkResult> getResults() {
		List<BenchmarkResult> benchmarks = ListConsumer.getResults();
		return benchmarks;
	}
	

}
