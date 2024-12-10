package com.model;



import CLI.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPoolingSystem {
    private final List<Tickets> ticketList = Collections.synchronizedList(new ArrayList<>());
    private final int maxCapacity;

    public TicketPoolingSystem(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(Tickets ticket) {
        while (ticketList.size() >= maxCapacity) {
            try {
                System.out.println("Ticket pool is full. Waiting for customers to buy tickets...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while waiting to add a ticket.", e);
            }
        }

        ticketList.add(ticket);
        System.out.println(ticket.getTicketName() + " added a ticket. Current pool size: " + ticketList.size());
        notifyAll();
    }

    public synchronized void getTicket(Tickets ticket) {
        while (ticketList.isEmpty()) {
            try {
                System.out.println("Ticket pool is empty. Waiting for vendors to add tickets...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while waiting to fetch a ticket.", e);
            }
        }

        ticketList.remove(0);
        System.out.println(ticket.getTicketName() + " retrieved a ticket. Current pool size: " + ticketList.size());
        notifyAll();
    }
}
