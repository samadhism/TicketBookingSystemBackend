package com.model;

public class Vendors implements Runnable {
    private TicketPoolingSystem ticketPool;
    private int vendorId;

    public Vendors(TicketPoolingSystem ticketPool, int vendorId) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Tickets vendorTicket = new Tickets(vendorId);
                ticketPool.addTicket(vendorTicket);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
