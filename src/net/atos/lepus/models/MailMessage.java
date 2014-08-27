package net.atos.lepus.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "mail")
@XmlRootElement(name = "MailMessage")
public class MailMessage {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	int id;
	@Column(name = "Sender")
	int from;
	@Column(name = "Recipient")
	int to;
	String subject;
	String message;
	
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
