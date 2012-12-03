package de.htwaalen.jubws.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElementWrapper;

import de.htwaalen.jubws.BenchmarkResult;



@WebService
public interface JUnitService {
	
    void runBenchmark(String path, String classname) throws MalformedURLException, ClassNotFoundException, IOException;
    
    
    @XmlElementWrapper(required = true)
    List<BenchmarkResult> getResults();
}

