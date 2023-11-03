package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

@ApplicationScoped
public class MessengerConsumer {
    
    @Inject
    ConnectionFactory connectionFactory;

    private String lastMessage;

    public String recive() {
        
        JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        JMSConsumer consumer = context.createConsumer(context.createQueue("message"));
        Message message = consumer.receive(1000);
        
        try {
            lastMessage = message.getBody(String.class);
        } catch (JMSException e) {
            lastMessage = "No more message in current queue!";
        }
        
        context.close();

        return lastMessage;
    }

}
