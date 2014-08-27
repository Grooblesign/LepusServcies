package net.atos.lepus.beans;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.jboss.logging.Logger;

public class SendMailBean {

	static Logger logger = Logger.getLogger(SendMailBean.class);  

	@Resource(mappedName = "java:/JmsXA")
	ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:jboss/exported/jms/queue/test")
	Queue testQueue;

	public SendMailBean() {}
	
	public void send(String bodyText) {
		Connection conn = null;
		
		try {
			conn = connectionFactory.createConnection();

			Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = sess.createProducer(testQueue);
			// producer.send(sess.createTextMessage(String.format("%s - %s", bodyText, System.currentTimeMillis())));
			producer.send(sess.createTextMessage(bodyText));

			logger.info("Message sent");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (JMSException e) {
					logger.error("Exception: " + e.getMessage());
				}
			}
		}
	}
}
