package restS;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectUsernameExceptionMapper implements ExceptionMapper<IncorrectUsernameException> {
    @Override public Response toResponse(IncorrectUsernameException e) { 
		return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build(); 
	}
}