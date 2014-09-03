package net.atos.lepus.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.atos.lepus.DAOs.UserDAO;
import net.atos.lepus.models.AuthenticationUser;
import net.atos.lepus.models.User;

import org.jboss.logging.Logger;

@Path("/authentication")
public class AuthenticationService {

	static Logger logger = Logger.getLogger(AuthenticationService.class);  
		
	@Inject UserDAO userDAO;
	
	@POST
	@Path("/wibble")
	@Produces("application/json")
	public Response getUserForWibbleInJson(@FormParam("wibble") String wibble) {
		return getUserForWibble(wibble);
	}
	
	@POST
	@Path("/wibble")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUserForWibbleInJson2(AuthenticationUser user) {
		return getUserForWibble(user.getWibble());
	}

	@POST
	@Path("/wibble")
	@Produces("application/xml")
	public Response getUserForWibbleInXML(@FormParam("wibble") String wibble) {
		return getUserForWibble(wibble);
	}
	
	private Response getUserForWibble(String wibble) {
		Response response= null;

		try {
			User user = userDAO.findByWibble(wibble);
			
			if (user == null) {
				response = Response.status(Status.NOT_FOUND).build();
			} else {
				response = Response.ok(user).build();
			}
		} catch (Exception exception) {
			logger.error("Exception: " + exception.getClass().toString() + " - " + exception.getMessage());
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}
	
	@POST
	@Path("/user")
	@Produces("application/json")
	public Response getUserForCredentialsInJson(@FormParam("username") String username, @FormParam("password") String password) {
		return getUserForCredentials(username, password);
	}
	
	@POST
	@Path("/user")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUserForCredentialsInJson2(AuthenticationUser user) {
		return getUserForCredentials(user.getUsername(), user.getPassword());
	}
	
	@POST
	@Path("/user")
	@Produces("application/xml")
	public Response getUserForCredentialsInXML(@FormParam("username") String username, @FormParam("password") String password) {
		return getUserForCredentials(username, password);
	}
	
	private Response getUserForCredentials(String username, String password) {
		Response response= null;

		try {
			User user = userDAO.findByName(username);
			
			if (user != null && !user.getPassword().equals(password)) {
				user = null;
			}

			if (user == null) {
				response = Response.status(Status.NOT_FOUND).build();
			} else {
				response = Response.ok(user).build();
			}
		} catch (Exception exception) {
			logger.error("Exception: " + exception.getClass().toString() + " - " + exception.getMessage());
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}
}
