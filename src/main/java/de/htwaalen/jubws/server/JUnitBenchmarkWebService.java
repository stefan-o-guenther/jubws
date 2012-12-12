package de.htwaalen.jubws.server;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElementWrapper;

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

