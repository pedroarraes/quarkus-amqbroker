package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@ApplicationScoped
public class MessengerProducer {
    
    @Inject
    ConnectionFactory connectionFactory;

    public void send(String sMessage) {
        JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        Queue queue = context.createQueue("message");
        
        context.createProducer().send(queue, sMessage);

        context.close();
    }
}
