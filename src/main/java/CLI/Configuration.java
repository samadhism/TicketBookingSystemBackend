package CLI;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;


    public Configuration(){
    }
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void displayConfiguration(){
        System.out.println("Total tickets:" + totalTickets);
        System.out.println("Ticket release rate:" + ticketReleaseRate);
        System.out.println("Customer retrieval rate:" + customerRetrievalRate);
        System.out.println("Max ticket capacity:" + maxTicketCapacity);
    }

    public int getMaxTicketCapacity(){
        return maxTicketCapacity;
    }

}
