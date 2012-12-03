package de.htwaalen.jubws.client;




import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import de.htwaalen.jubws.BenchmarkResult;
import de.htwaalen.jubws.server.JUnitService;


public class Client {


    public static void main(String args[]) throws Exception {
        Service service = Service.create(new QName("http://server.jubws.htwaalen.de/", "JUnitService"));

        String endpointAddress = "http://localhost:9000/junitservice";

        service.addPort(new QName("http://server.jubws.htwaalen.de/", "JUnitServicePort"),
        				SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        
        JUnitService junit = service.getPort(JUnitService.class);
        
        int sizeBefore = junit.getResults().size();
        
        
        junit.runBenchmark("de.htwaalen.jubws.DemoBenchmark");
        
        List<BenchmarkResult> results = junit.getResults();
        
        int sizeAfter = results.size();
        
        System.out.println("sizeBefore: "+sizeBefore+" sizeAfter: "+sizeAfter);


        for(BenchmarkResult r : results)
        	System.out.println(r);
        
    }

}
