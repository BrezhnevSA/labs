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
								   @QueryParam("position") String position, @QueryParam("status") int status) {
		List<Person> persons = dao.getPersons(name, surname, age, username, position, status);
		return persons;
	}
	
    @Path("/addPerson")
    @GET public int addHuman(@QueryParam("name") String name, @QueryParam("surname") String surname,
							 @QueryParam("age") int age, @QueryParam("username") String username,
							 @QueryParam("position") String position, @QueryParam("status") int status) throws IncorrectUsernameException { 
		if (username == "" || username == null)
            throw new IncorrectUsernameException("username not found");		
		return dao.addPerson(name, surname, age, username, position, status); 
	}

    @Path("/removePerson")
    @GET public int removeHuman(@QueryParam("username") String username) throws IncorrectUsernameException { 
		if (username == "" || username == null)
            throw new IncorrectUsernameException("username not found");
		return dao.removePerson(username); 
	}

    @Path("/changePerson")
    @GET public int changeHuman(@QueryParam("name") String name, @QueryParam("surname") String surname,
                                @QueryParam("age") int age, @QueryParam("username") String username,
								@QueryParam("position") String position, @QueryParam("status") int status) throws IncorrectUsernameException { 
		if (username == "" || username == null)
            throw new IncorrectUsernameException("username not found");
		return dao.changePerson(name, surname, age, username, position, status); 
	}
}