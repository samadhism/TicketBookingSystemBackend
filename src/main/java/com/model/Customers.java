package com.model;

public class Customers implements Runnable {
    private TicketPoolingSystem ticketPoolingSystem;
    private int customerId;

    public Customers(TicketPoolingSystem ticketpool, int customerId) {
        this.ticketPoolingSystem = ticketpool;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Tickets customerticket = new Tickets(customerId);
                ticketPoolingSystem.getTicket(customerticket);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
