package com.controller;

import com.model.TicketPoolingSystem;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/ticket-controller")
public class TicketController {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numberOfVendors;
    private int numberOfCustomers;
    private TicketPoolingSystem ticketPool;


    @PostMapping("/configuration")
    public String Configuration() {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;


        return"Configuration successfully";
    }
}
