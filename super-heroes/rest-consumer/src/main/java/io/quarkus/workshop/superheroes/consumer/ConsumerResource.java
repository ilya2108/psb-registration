package io.quarkus.workshop.superheroes.consumer;

import io.smallrye.mutiny.Multi;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Incoming;

public class ConsumerResource {

    @Incoming("queue")
    public void consumeOperation(Multi<Operation> operation) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}