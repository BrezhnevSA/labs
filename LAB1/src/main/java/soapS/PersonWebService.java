package soapS;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
@WebService(serviceName = "PersonService")
public class PersonWebService {
    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons();
        return persons;
    }

    @WebMethod(operationName = "searchPersons")
    public List<Person> searchPersons(String term) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String name = "", surname = "", username = "", position = "";
        int age = -1, status = -1;
        for (String arg : term.split(";")) {
            if (arg.split("=")[0].equals("name")) {
                name = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("surname")) {
                surname = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("username")) {
                username = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("position")) {
                position = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("age")) {
                age = Integer.parseInt(arg.split("=")[1]);
            }
            if (arg.split("=")[0].equals("status")) {
                status = Integer.parseInt(arg.split("=")[1]);
            }
        }
        List<Person> persons = dao.searchPersons(name, surname, age, username, position, status);
        return persons;
    }

}