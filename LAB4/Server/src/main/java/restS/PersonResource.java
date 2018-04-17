package restS;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {
	@Resource(lookup = "jdbc/data")
    private DataSource dataSource;
	
	@GET
	public List<Person> getPersons(@QueryParam("name") String name, @QueryParam("surname") String surname, 
								   @QueryParam("age") int age, @QueryParam("username") String username, 
								   @QueryParam("position") String position,@QueryParam("status") int status) {
		List<Person> persons = dao.getPersons(name, surname, age, username, position, status);
		return persons;
	}
}