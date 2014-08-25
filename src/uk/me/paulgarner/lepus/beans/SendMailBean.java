package uk.me.paulgarner.lepus.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

@Named
@Stateless
public class SendMailBean {

	@Resource(mappedName = "java:/JmsXA")
	ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:jboss/exported/jms/queue/test")
	Queue testQueue;

	public SendMailBean() {}
	
	public void send(String bodyText) {
		// Put a message on the queue
		System.out.println("Here we go");
		System.out.println(connectionFactory);		
		System.out.println(testQueue);

		Connection conn = null;
		try {
			conn = connectionFactory.createConnection();

			System.out.println("stage 1");

			Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

			System.out.println("stage 2");

			MessageProducer producer = sess.createProducer(testQueue);
			producer.send(sess.createTextMessage(bodyText));

			System.out.println("Message sent");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (JMSException e) {
					System.out.println("Exception: " + e.getMessage());
				}
			}
		}
	}
}
