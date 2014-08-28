package net.atos.lepus.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.atos.lepus.models.MailMessage;

import org.jboss.logging.Logger;

@MessageDriven(mappedName="queue/mail", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination",
        						  propertyValue = "queue/mail")
    })
public class ReceiveMailBean implements MessageListener {

	static Logger logger = Logger.getLogger(ReceiveMailBean.class);  
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void onMessage(Message message) {
				
		logger.info("======= Message =======");
		logger.info(message);
		
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)message;
			try {
				logger.info(textMessage.getText());

				MailMessage mailMessage = new MailMessage();
				mailMessage.setFrom(100);
				mailMessage.setTo(101);
				mailMessage.setSubject("Test");
				mailMessage.setMessage(textMessage.getText());
				
				entityManager.persist(mailMessage);
				
			} catch (Exception ex) {
				logger.error("Exception: " + ex.getMessage());
			}
		}
		
		logger.info("=======================");
	}
}
