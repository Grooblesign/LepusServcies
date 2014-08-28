package net.atos.lepus.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.atos.lepus.models.User;

import org.jboss.logging.Logger;

@Path("/authentication")
public class AuthenticationService {

	static Logger logger = Logger.getLogger(AuthenticationService.class);  

	@PersistenceContext
	EntityManager entityManager;
		
	@POST
	@Path("/wibble")
	@Produces("application/json")
	public Response getUserForWibbleInJson(@FormParam("wibble") String wibble) {

		Response response= null;

		try {
			User user = getUserForWibble(wibble);
			
			if (user == null) {
				response = Response.status(Status.NOT_FOUND).build();;
			} else {
				response = Response.ok(user).build();
			}
		} catch (Exception exception) {
			logger.error("Exception: " + exception.getClass().toString() + " - " + exception.getMessage());;
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}
	
	@POST
	@Path("/wibble")
	@Produces("application/xml")
	public Response getUserForWibbleInXML(@FormParam("wibble") String wibble) {

		Response response= null;

		try {
			User user = getUserForWibble(wibble);
			
			if (user == null) {
				response = Response.status(Status.NOT_FOUND).build();;
			} else {
				response = Response.ok(user).build();
			}
		} catch (Exception exception) {
			logger.error("Exception: " + exception.getClass().toString() + " - " + exception.getMessage());;
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}
	
	private User getUserForWibble(String wibble) {
		
		String queryString = "from User u where u.wibble = (:wibble)";

		Query query = entityManager.createQuery(queryString).setParameter("wibble", wibble);

		User user = null;
		try {
			user = (User) query.getSingleResult();
			
			if (user != null) {
				if (!user.getWibble().equals(wibble)) {
					user = null;
				}
			}
		} catch (NoResultException exception) {
			
		}
		
		return user;
	}
	
	@POST
	@Path("/user")
	@Produces("application/json")
	public Response getUserForCredentialsInJson(@FormParam("username") String username, @FormParam("password") String password) {
		
		Response response= null;

		try {
			User user = getUserForCredentials(username, password);
			
			if (user == null) {
				response = Response.status(Status.NOT_FOUND).build();;
			} else {
				response = Response.ok(user).build();
			}
		} catch (Exception exception) {
			logger.error("Exception: " + exception.getClass().toString() + " - " + exception.getMessage());;
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}
	
	@POST
	@Path("/user")
	@Produces("application/xml")
	public Response getUserForCredentialsInXML(@FormParam("username") String username, @FormParam("password") String password) {
		
		Response response= null;

		try {
			User user = getUserForCredentials(username, password);
			
			if (user == null) {
				response = Response.status(Status.NOT_FOUND).build();;
			} else {
				response = Response.ok(user).build();
			}
		} catch (Exception exception) {
			logger.error("Exception: " + exception.getClass().toString() + " - " + exception.getMessage());;
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}
	
	private User getUserForCredentials(String username, String password) {
		
		String queryString = "from User u where u.name = (:name) and u.password = (:password)";

		Query query = entityManager.createQuery(queryString)
				.setParameter("name", username)
				.setParameter("password", password);
		
		User user = null;
		
		try {
			user = (User) query.getSingleResult();
			
			if (user != null) {
				if (!(user.getName().equals(username) && user.getPassword().equals(password))) {
					user = null;
				}
			}
		} catch (NoResultException exception) {
		
		}

		return user;
	}
}
