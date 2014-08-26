package net.atos.lepus.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

@MessageDriven(mappedName="queue/mail", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination",
        						  propertyValue = "queue/test")
    })
public class ReceiveMailBean implements MessageListener {

	static Logger logger = Logger.getLogger(ReceiveMailBean.class);  
	
	@Override
	public void onMessage(Message message) {
				
		logger.info("======= Message =======");
		logger.info(message);
		
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)message;
			try {
				logger.error(textMessage.getText());
			} catch (Exception ex) {
				logger.error("Exception: " + ex.getMessage());
			}
		}
		
		logger.info("=======================");
	}
}
