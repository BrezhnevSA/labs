package soapS;

import javax.xml.ws.Endpoint;
public class App {
    public static void main(String[] args) {
        String url = "http://0.0.0.0:8085/PersonService";
        Endpoint.publish(url, new PersonWebService());
    }
}