package com.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPoolingSystem {
    private final List<Tickets> ticketList = Collections.synchronizedList(new ArrayList<>());
    private final int maxCapacity;

    // Shared log storage
    private final List<String> logs = Collections.synchronizedList(new ArrayList<>());

    public TicketPoolingSystem(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Expose logs for external access
    public List<String> getLogs() {
        return new ArrayList<>(logs); // Return a copy to avoid modification
    }

    public synchronized void addTicket(Tickets ticket) {
        while (ticketList.size() >= maxCapacity) {
            try {
                String logMessage = "Ticket pool is full. Waiting for customers to buy tickets...";
                logs.add(logMessage);
                System.out.println(logMessage);
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while waiting to add a ticket.", e);
            }
        }

        ticketList.add(ticket);
        String logMessage = ticket.getTicketName() + " added a ticket. Current pool size: " + ticketList.size();
        logs.add(logMessage);
        System.out.println(logMessage);
        notifyAll();
    }

    public synchronized void getTicket(Tickets ticket) {
        while (ticketList.isEmpty()) {
            try {
                String logMessage = "Ticket pool is empty. Waiting for vendors to add tickets...";
                logs.add(logMessage);
                System.out.println(logMessage);
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while waiting to fetch a ticket.", e);
            }
        }

        ticketList.remove(0);
        String logMessage = ticket.getTicketName() + " retrieved a ticket. Current pool size: " + ticketList.size();
        logs.add(logMessage);
        System.out.println(logMessage);
        notifyAll();
    }
}



