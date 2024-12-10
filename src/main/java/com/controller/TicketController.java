package com.controller;

import com.model.Customers;
import com.model.TicketPoolingSystem;
import com.model.Vendors;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/ticket-controller")
public class TicketController {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int vendorCount;
    private int customerCount;
    private TicketPoolingSystem ticketPool;


    @PostMapping("/configuration")
    public String Configuration(@RequestParam int totalTickets, @RequestParam int ticketReleaseRate,@RequestParam int customerRetrievalRate,@RequestParam int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;

        ticketPool = new TicketPoolingSystem(maxTicketCapacity);
        return "Configuration completed successfully";
    }

    @PostMapping("/set-vendors")
    public String setVendors(@RequestParam int vendorCount){
        this.vendorCount = vendorCount;
        return "Vendor Count set successfully";

    }

    @PostMapping("/set-customers")
    public String setCustomers(@RequestParam int customerCount){
        this.customerCount = customerCount;
        return "Customer Count set successfully";
    }

    @PostMapping("/start")
    public String startTheSystem(){
        if (vendorCount > 0) {
            for (int i = 0; i < vendorCount; i++) {
                Vendors vendor = new Vendors(ticketPool,(i+1));
                new Thread(vendor).start();
            }
            for (int i = 0; i < customerCount; i++) {
                Customers customer = new Customers(ticketPool,(i+1));
                new Thread(customer).start();
            }
        }
        return "System started ";
    }
}