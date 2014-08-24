package uk.me.paulgarner.lepus.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MailMessage")
public class MailMessage {
	int from;
	int to;
	String subject;
	String body;
	
	@XmlElement
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	
	@XmlElement
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	
	@XmlElement
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@XmlElement
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
