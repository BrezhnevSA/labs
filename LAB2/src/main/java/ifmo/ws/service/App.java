package ifmo.ws.service;

import javax.xml.ws.Endpoint;

public class App {

    public static void main(String[] args) {
        String url = "http://localhost:8081/lab2";
        Endpoint.publish(url, new BookWebService());
    }

}
