package de.htwaalen.jubws.client;




import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import de.htwaalen.jubws.BenchmarkResult;
import de.htwaalen.jubws.server.JUnitBenchmarkWebService;


public class Client {


    public static void main(String args[]) throws Throwable {
        Service service = Service.create(new QName("http://server.jubws.htwaalen.de/", "JUnitBenchmarkWebService"));

        String endpointAddress = "http://localhost:8080/jubws/services/junitservice";

        service.addPort(new QName("http://server.jubws.htwaalen.de/", "JUnitBenchmarkWebServicePort"),
        				SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        
        JUnitBenchmarkWebService junit = service.getPort(JUnitBenchmarkWebService.class);
        

        
        int token = junit.enqueueBenchmark("file:///home/lucid/remote/stash/Dropbox/htw-aalen/Distributed Applications/Project/benchmarks/target/benchmarks-1.0.0.jar","de.htwaalen.benchmarks.DemoBenchmark");
        int token2 = junit.enqueueBenchmark("file:///home/lucid/remote/stash/Dropbox/htw-aalen/Distributed Applications/Project/benchmarks/target/benchmarks-1.0.0.jar","de.htwaalen.benchmarks.DemoBenchmark");
        
        while(! (junit.isDone(token) || junit.isDone(token2)));
        
       BenchmarkResult results = junit.getResult(token);
       
       System.out.println(results.toXML());
        

    }

}
