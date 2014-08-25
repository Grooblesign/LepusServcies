package uk.me.paulgarner.lepus.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import uk.me.paulgarner.lepus.models.MailMessage;

@Path("/mail")
public class MailService {
	
	@GET
	@Path("/messagesJson")
	@Produces("application/json")
	public List<MailMessage> getMessagesInJson() {
		
		List<MailMessage> messages = new ArrayList<MailMessage>();
		
		MailMessage message = new MailMessage();
		
		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello");
		message.setBody("Hello World in Json");
		
		messages.add(message);

		message = new MailMessage();
		
		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello again");
		message.setBody("Hello World 2 in Json");
		
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
		message.setBody("Hello World in XML");
		
		messages.add(message);

		message = new MailMessage();
		
		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello again");
		message.setBody("Hello World 2 in XML");
		
		messages.add(message);

		return messages;
	}
}
