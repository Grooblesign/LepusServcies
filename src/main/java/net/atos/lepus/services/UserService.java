package net.atos.lepus.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.atos.lepus.DAOs.UserDAO;
import net.atos.lepus.models.User;

import org.jboss.logging.Logger;

@Path("/user")
public class UserService {
	static Logger logger = Logger.getLogger(AuthenticationService.class);  
	
	@Inject UserDAO userDAO;

	@GET
	@Path("/")
	@Produces("application/xml")
	public Collection<User> getUsers() {
		
		return userDAO.findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public Response getUser(@PathParam("id") int id) {
		logger.info("getUser in");
		
		Response response = null;
		
		User user = userDAO.find(id);
		
		if (user == null) {
			response = Response.status(Status.NOT_FOUND).build();
		} else {
			response = Response.ok(user).build();
		}
		
		logger.info("getUser out");

		return response;
	}
	
	@POST
	@Path("/")
	@Produces("application/xml")
	public Response postUser(@FormParam("name") String name, @FormParam("password") String password, @FormParam("wibble") String wibble) {
		User user = new User();
		
		user.setName(name);
		user.setWibble(wibble);
		user.setPassword(password);
		
		userDAO.insert(user);
		
		return Response.ok(user).build();
	}
}
