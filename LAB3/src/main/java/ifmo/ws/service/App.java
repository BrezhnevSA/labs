package ifmo.ws.service;

import javax.xml.ws.Endpoint;

public class App {

    public static void main(String[] args) {
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
        String url = "http://localhost:8081/lab2";
        Endpoint.publish(url, new BookWebService());
    }

}
