package soaps;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
public class WebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        PersonService personService = new PersonService(url);

        System.out.println("getPersons: ");
        List<Person> persons = personService.getPersonWebServicePort().getPersons();
        for (Person person : persons) {
            System.out.println("name: " + person.getName() +
                             ", surname: " + person.getSurname() +
                             ", age: " + person.getAge() +
                             ", position: " + person.getPosition() +
                             ", username: " + person.getUsername() +
                             ", status: " + person.getStatus());
        }
        System.out.println("Total persons: " + persons.size());

        String term = "name=Иван;age=27";
        System.out.println("\nsearchPersons by: " + term);
        persons = personService.getPersonWebServicePort().searchPersons(term);
        for (Person person : persons) {
            System.out.println("name: " + person.getName() +
                    ", surname: " + person.getSurname() +
                    ", age: " + person.getAge() +
                    ", position: " + person.getPosition() +
                    ", username: " + person.getUsername() +
                    ", status: " + person.getStatus());
        }
        System.out.println("Total persons: " + persons.size());
    }
}