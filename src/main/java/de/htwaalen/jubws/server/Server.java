package de.htwaalen.jubws.server;



import javax.xml.ws.Endpoint;

import de.htwaalen.jubws.JUnitBenchmarkServiceImpl;


public class Server {

    public static void main(String args[]) throws Exception {
        System.out.println("Starting Server");
        JUnitBenchmarkWebServiceImpl implementor = new JUnitBenchmarkWebServiceImpl();
        implementor.setBenchmarkService(new JUnitBenchmarkServiceImpl());
        String address = "http://localhost:9000/junitservice";
        Endpoint.publish(address, implementor);
        System.out.println("Server ready");
    }
}
