package net.atos.lepus.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.logging.Logger;

@WebService
public class HelloSoapService {

	static Logger logger = Logger.getLogger(MailService.class);

	private String message = new String("Hello, ");

	@WebMethod
	public String sayHello(String name) {
		return message + name + ".";
	}
}
