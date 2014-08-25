package uk.me.paulgarner.lepus.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import uk.me.paulgarner.lepus.models.MailMessage;

@Path("/")
public class MailService {
	
	@GET
	@Path("/helloworld")
	@Produces("text/plain")
	public String getMessage() {
		return "Hello World in text";
	}
	
	@GET
	@Path("/helloworldJson")
	@Produces("application/json")
	public Map<String, String> getMessageInJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Message", "Hello World in json"); 
		
		return map;
	}
	
	@GET
	@Path("/messages")
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
	@Path("/messagesx")
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

	@GET
	@Path("/helloworldXML")
	@Produces("application/xml")
	public MailMessage getMessageInXML() {
		
		MailMessage message = new MailMessage();
		
		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello");
		message.setBody("Hello World in XML");
		
		return message;
	}
	
}
