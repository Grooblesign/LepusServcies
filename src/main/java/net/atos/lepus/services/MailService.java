package net.atos.lepus.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.atos.lepus.beans.SendMailBean;
import net.atos.lepus.models.MailMessage;

import org.jboss.logging.Logger;

@Path("/mail")
public class MailService {

	static Logger logger = Logger.getLogger(MailService.class);  
	
	@PersistenceContext
	EntityManager entityManager;

	@Inject SendMailBean bean = new SendMailBean();

	@GET
	@Path("/messagesJson")
	@Produces("application/json")
	public Collection<MailMessage> getMessagesInJson() {

		Query query = entityManager.createQuery("SELECT m FROM MailMessage m");
	    @SuppressWarnings("unchecked")
		Collection<MailMessage> messages = (Collection<MailMessage>) query.getResultList();
	    
		return messages;
	}

	@GET
	@Path("/message/{id}")
	@Produces("application/json")
	public Response getMessageJson(@PathParam("id") int id) {

		return getMessage(id);
	}
	
	@GET
	@Path("/message/{id}")
	@Produces("application/xml")
	public Response getMessageInXML(@PathParam("id") int id) {

		return getMessage(id);
	}

	private Response getMessage(int id) {
		
		Response response= null;
		
		try {
			MailMessage message = entityManager.find(MailMessage.class, id);
			
			if (message == null) { 
				response = Response.status(Status.NOT_FOUND).build();;
			} else {
				response = Response.ok(message).build();
			}
		} catch (Exception exception) {
			logger.error("Exception: " + exception.getClass().toString() + " - " + exception.getMessage());;
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}

	@GET
	@Path("/messagesXML")
	@Produces("application/xml")
	public Collection<MailMessage> getMessagesInXML() {

		Query query = entityManager.createQuery("SELECT m FROM MailMessage m");
	    @SuppressWarnings("unchecked")
		Collection<MailMessage> messages = (Collection<MailMessage>) query.getResultList();
		
		return messages;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/messagesXML")
	public Response sendMessage(@FormParam("message") String message) {

		bean.send(message);

        return Response.ok("message=" + message).build();
    }		
}
