package net.atos.lepus.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.atos.lepus.models.MailMessage;

@Path("/hello")
public class HelloService {

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
		map.put("Message", "Hello World in JSON"); 
		return map;
	}
	
	@GET
	@Path("/helloworldXML")
	@Produces("application/xml")
	public MailMessage getMessageInXML() {
		
		MailMessage message = new MailMessage();
		
		message.setFrom(100000);
		message.setTo(100000);
		message.setSubject("Hello");
		message.setMessage("Hello World in XML");
		
		return message;
	}
}
