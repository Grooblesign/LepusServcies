package uk.me.paulgarner.lepus.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="java:jboss/exported/jms/queue/test", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination",
        						  propertyValue = "java:jboss/exported/jms/queue/test")
    })
public class ReceiveMailBean implements MessageListener {

	public ReceiveMailBean() {}

	@Override
	public void onMessage(Message message) {
		System.out.println("======= Message =======");
		System.out.println(message);
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)message;
			try {
				System.out.println(textMessage.getText());
			} catch (Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
		}
		System.out.println("=======================");
	}
}
