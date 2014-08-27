package net.atos.lepus.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.atos.lepus.beans.SendMailBean;
import net.atos.lepus.models.MailMessage;

@Path("/mail")
public class MailService {

	@Inject SendMailBean bean;
	
	@GET
	@Path("/messagesJson")
	@Produces("application/json")
	public List<MailMessage> getMessagesInJson() {

		List<MailMessage> messages = new ArrayList<MailMessage>();

		MailMessage message = new MailMessage();

		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello");
		message.setMessage("Hello World in Json");

		messages.add(message);

		message = new MailMessage();

		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello again");
		message.setMessage("Hello World 2 in Json");

		messages.add(message);
		
		return messages;
	}

	@GET
	@Path("/messagesXML")
	@Produces("application/xml")
	public List<MailMessage> getMessagesInXML() {

		List<MailMessage> messages = new ArrayList<MailMessage>();

		MailMessage message = new MailMessage();

		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello");
		message.setMessage("Hello World in XML");

		messages.add(message);

		message = new MailMessage();

		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello again");
		message.setMessage("Hello World 2 in XML");

		messages.add(message);

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
