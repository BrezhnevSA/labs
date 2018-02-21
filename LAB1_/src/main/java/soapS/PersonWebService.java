package soapS;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;

@WebService(serviceName = "PersonService")
public class PersonWebService {
	@Resource(lookup = "jdbc/ifmo-ws")
	private DataSource dataSource;
	
    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        List<Person> persons = dao.getPersons();
        return persons;
    }

    @WebMethod(operationName = "searchPersons")
    public List<Person> searchPersons(String term) {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
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
	
	private Connection getConnection() {
		Connection result = null;
		try {
			result = dataSource.getConnection();
		} 
		catch (SQLException ex) {
			Logger.getLogger(PersonWebService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

}