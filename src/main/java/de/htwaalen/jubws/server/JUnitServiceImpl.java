package de.htwaalen.jubws.server;


import java.net.MalformedURLException;
import java.util.List;

import javax.jws.WebService;

import org.junit.runner.JUnitCore;


import de.htwaalen.jubws.BenchmarkResult;
import de.htwaalen.jubws.ListConsumer;

@WebService(endpointInterface = "de.htwaalen.jubws.server.JUnitService")
public class JUnitServiceImpl implements JUnitService {

	private ClassLoader classloader;
	
	public JUnitServiceImpl() {
			classloader = JUnitServiceImpl.class.getClassLoader();
	}
	
	@Override
	public void runBenchmark(String classname) throws MalformedURLException, ClassNotFoundException {
		System.out.println("called run(\"" + classname + "\")");
		
		Class<?> clazz = classloader.loadClass(classname);
		JUnitCore.runClasses(clazz);
	}

	@Override
	public List<BenchmarkResult> getResults() {
		List<BenchmarkResult> benchmarks = ListConsumer.getResults();
		return benchmarks;
	}
	

}
