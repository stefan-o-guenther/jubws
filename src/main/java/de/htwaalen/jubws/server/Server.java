package de.htwaalen.jubws.server;



import javax.xml.ws.Endpoint;


public class Server {

    public static void main(String args[]) throws Exception {
        System.out.println("Starting Server");
        JUnitBenchmarkWebService implementor = new JUnitBenchmarkWebServiceImpl();
        String address = "http://localhost:9000/junitservice";
        Endpoint.publish(address, implementor);

        System.out.println("Server ready");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
