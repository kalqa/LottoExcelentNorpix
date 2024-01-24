package com.norpix.numberreceiver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNumberReceiverRepositoryTestImplementation implements NumberReceiverRepository {
    Map<String, Ticket> inMemoryDatabase = new ConcurrentHashMap<>();
    @Override
    public Ticket save(Ticket ticket) {
        inMemoryDatabase.put(ticket.clientTickedId(), ticket);
        return ticket;
    }
}