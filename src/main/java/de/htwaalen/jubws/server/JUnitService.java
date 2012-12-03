package de.htwaalen.jubws.server;

import java.net.MalformedURLException;
import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElementWrapper;

import de.htwaalen.jubws.BenchmarkResult;



@WebService
public interface JUnitService {
	
    void runBenchmark(String classname) throws MalformedURLException, ClassNotFoundException;
    
    
    @XmlElementWrapper(required = true)
    List<BenchmarkResult> getResults();
}

