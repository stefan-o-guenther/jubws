package de.htwaalen.jubws.server;

import java.util.concurrent.ExecutionException;

import javax.jws.WebService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ContextLoader;



import de.htwaalen.jubws.BenchmarkResult;
import de.htwaalen.jubws.JUnitBenchmarkService;
import de.htwaalen.jubws.JUnitBenchmarkService.BenchmarkNotDoneException;
import de.htwaalen.jubws.JUnitBenchmarkService.InvalidTokenException;



@WebService(endpointInterface = "de.htwaalen.jubws.server.JUnitBenchmarkWebService")
public class JUnitBenchmarkWebServiceImpl implements JUnitBenchmarkWebService {

	@Autowired
	private JUnitBenchmarkService benchmarkService;
	
	public JUnitBenchmarkWebServiceImpl() {
	}
	
	@Override
	public int enqueueBenchmark(String path, String classname) {
		//benchmarkService = (JUnitBenchmarkService)ContextLoader.getCurrentWebApplicationContext().getBean("benchmarkService");
		System.out.println(benchmarkService);
		return benchmarkService.enqueueBenchmark(path, classname);
	}

	@Override
	public boolean isDone(int token) throws InvalidTokenException {
		return benchmarkService.isDone(token);
	}

	@Override
	public BenchmarkResult getResult(int token) throws ExecutionException,
			BenchmarkNotDoneException, InvalidTokenException {
		return benchmarkService.getResult(token);
	}

	
}
