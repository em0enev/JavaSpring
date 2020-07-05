package com.botronsoft.internship.Factories;

import com.botronsoft.internship.models.Ticket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TicketFactory {
    private int totalCustomers = 0;


    public Ticket getTicket(String serviceId, HashMap<String, Queue<Ticket>>  serviceQueue){
        totalCustomers++;

        if (!serviceQueue.containsKey(serviceId)){
            serviceQueue.put(serviceId, new LinkedList<>());
        }

        Queue<Ticket> currentServiceQueue = serviceQueue.get(serviceId);

        Ticket ticket = new Ticket(serviceId, totalCustomers);
        currentServiceQueue.add(ticket);
        ticket.setQueueCount(currentServiceQueue.size());

        return ticket;
    }
}
