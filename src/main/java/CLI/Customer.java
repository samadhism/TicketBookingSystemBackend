package CLI;

public class Customer implements Runnable {
    private int customerID;
    private String customerName;
    private int retrievalInterval;
    private TicketPool ticketPool;

    public Customer(int customerID, int retrievalInterval, TicketPool ticketPool,String customerName) {
        this.customerID = customerID;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Ticket ticket = new Ticket(customerName);
                ticketPool.buyTicket(ticket);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

