package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/messenger")
@Produces("application/plain")
public class MessengerResource {

    @Inject
    MessengerProducer messengerProducer;

    @Inject
    MessengerConsumer messengerConsumer;
    
    @POST
    @Path("/send")
    public void send(String sMessage) {
        messengerProducer.send(sMessage);
    }

    @GET
    @Path("/recive")
    public String recive() {
        return messengerConsumer.recive();
    }
}
