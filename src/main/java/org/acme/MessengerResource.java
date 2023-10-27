package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/messenger")
public class MessengerResource {

    @Inject
    MessengerProducer messengerProducer;

    @Inject
    MessengerConsumer messengerConsumer;
    
    @POST
    public void send(String sMessage) {
        messengerProducer.send(sMessage);
    }

    @GET
    public String recive() {
        return messengerConsumer.recive();
    }
}
