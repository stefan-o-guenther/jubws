package de.htwaalen.jubws.server;

import java.util.concurrent.ExecutionException;

import javax.jws.WebService;

import de.htwaalen.jubws.BenchmarkResult;



@WebService
public interface JUnitBenchmarkWebService {
	
	class BenchmarkNotDoneException extends RuntimeException{
	}
	class InvalidTokenException extends RuntimeException{
	}
	
    int enqueueBenchmark(String path, String classname);
    
    boolean isDone(int token) throws InvalidTokenException;
    

    BenchmarkResult getResult(int token) throws ExecutionException, BenchmarkNotDoneException, InvalidTokenException ;
}

