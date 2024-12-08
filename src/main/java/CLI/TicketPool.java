package CLI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private ArrayList<Ticket> tickets;
    private List<Ticket> synchronizedTickets;
    private int maxTickets;
    private int totalTicket;


    public TicketPool(int maxTickets) {
        this.maxTickets = maxTickets;
        tickets = new ArrayList<>();
        synchronizedTickets = Collections.synchronizedList(tickets);
    }


    public synchronized void addTicket(Ticket ticket) {
       while (synchronizedTickets.size() > maxTickets){
           try {
               System.out.println("TicketPool is full..!");
               wait();

           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

       }

       synchronizedTickets.add(ticket);
       System.out.println(ticket.getTicketName() + "Added ticket" + synchronizedTickets.size());
       notifyAll();

    }

    public synchronized void buyTicket(Ticket ticket) {
        while (synchronizedTickets.isEmpty()){
            try {
                System.out.println("TicketPool is empty..!");
                wait();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        synchronizedTickets.remove(0);
        System.out.println(ticket.getTicketName() + "Removed ticket" + synchronizedTickets.size());
        notifyAll();
    }


}