package CLI;


public class Vendor implements Runnable {  //To perform independently actions
    private int vendorId;
    public String vendorName;
    private int ticketPerRelease;
    private int releaseInterval;
    private TicketPool ticketPool;


    public Vendor(int vendorId,int ticketPerRelease, int releaseInterval,TicketPool ticketPool,String vendorName) {
        this.vendorId = vendorId;
        this.releaseInterval = releaseInterval;
        this.ticketPerRelease = ticketPerRelease;
        this.ticketPool = ticketPool;
        this.vendorName = vendorName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Ticket ticket = new Ticket(vendorName);
                ticketPool.addTicket(ticket);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}

