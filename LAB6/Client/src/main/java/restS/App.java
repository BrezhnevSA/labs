package restS;

import com.sun.istack.internal.Nullable;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.MediaType;
import java.net.MalformedURLException;
import java.util.List;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class App {
    private final static String url = "http://localhost:8080/rest/humans";
    public static final int STATUS_GOOD = 0, STATUS_ERROR = 1, STATUS_BAD_REQUEST = 2;
   
    public static void main(String[] args) {
        int res;
        System.out.println("add");
        res = addHuman(client, "Thomas", "Muller", 45, "ThomMuler", "Main", 1 );
        System.out.print("add: " + res);
        System.out.println("update");
        res = changeHuman(client, "Thomas", "Muller", 35, "ThomMuler", "Main", 1 );
        System.out.print("update: " + getStatus(res));
        System.out.println("delete");
        res = removeHuman(client, "ThomMuller");
        System.out.print("delete: " + getStatus(res));
        client.destroy();
    }
	
	private static void printToConsole(List<Person> persons) {
        for (Person person : persons)
            System.out.println(person.name + " " + person.surnname + ' ' + person.username + ' ' + person.age + ' ' + person.position + ' ' + person.status);
    }

    public static String getStatus(int status) {
        switch (status) {
            case STATUS_GOOD:
                return "good";
            case STATUS_ERROR:
                return "error";
            case STATUS_BAD_REQUEST:
                return "bad_request";
        }
        return "so bad";
    }
	
    private static List<Person> getAllPersons(Client client, @Nullable WebResource webResource) {
        if (webResource == null)
            webResource = client.resource(url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode())
            throw new IllegalStateException("Request failed");
        GenericType<List<Person>> type = new GenericType<List<Person>>() {};
        return response.getEntity(type);
    }

    private static List<Person> getAllPersons(Client client, String name, String surname, int age, String username, String position, int status) {
        WebResource webResource = client.resource(url);
        webResource = webResource.queryParam("name", name);
        webResource = webResource.queryParam("surname", surname);
        webResource = webResource.queryParam("age", String.valueOf(age));
		webResource = webResource.queryParam("username", username);
        webResource = webResource.queryParam("position", position);
		webResource = webResource.queryParam("status", String.valueOf(status));
        return getAllPersons(client, webResource);
    }

    private static int addPerson(Client client, String name, String surname, int age, String username, String position, int status) {
        WebResource webResource = client.resource(url + "/addPerson");
        webResource = webResource.queryParam("name", name);
        webResource = webResource.queryParam("surname", surname);
        webResource = webResource.queryParam("age", String.valueOf(age));
		webResource = webResource.queryParam("username", username);
        webResource = webResource.queryParam("position", position);
		webResource = webResource.queryParam("status", String.valueOf(status));
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode())
            throw new IllegalStateException("Request failed");
        GenericType<Integer> type = new GenericType<Integer>() {};
        return response.getEntity(type);
    }

    private static int removePerson(Client client, String username) {
        WebResource webResource = client.resource(url + "/removePerson");
        webResource = webResource.queryParam("username", username);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode())
            throw new IllegalStateException("Request failed");
        GenericType<Integer> type = new GenericType<Integer>() {};
        return response.getEntity(type);
    }

    private static int changePerson(Client client, String name, String surname, int age, String username, String position, int status) {
        WebResource webResource = client.resource(url + "/changePerson");
        webResource = webResource.queryParam("name", name);
        webResource = webResource.queryParam("surname", surname);
        webResource = webResource.queryParam("age", String.valueOf(age));
		webResource = webResource.queryParam("username", username);
        webResource = webResource.queryParam("position", position);
		webResource = webResource.queryParam("status", String.valueOf(status));
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode())
            throw new IllegalStateException("Request failed");
        GenericType<Integer> type = new GenericType<Integer>() {};
        return response.getEntity(type);
    }		
}