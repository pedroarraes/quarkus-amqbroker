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
        
        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            
            JMSConsumer consumer = context.createConsumer(context.createQueue("message"));

            Message message = consumer.receive();
            
            if (message == null) 
                return null;
            
            lastMessage = message.getBody(String.class);
            
            context.close();
            consumer.close();
            
            return lastMessage;
            

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
