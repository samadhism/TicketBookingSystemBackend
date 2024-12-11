package com.controller;

import com.model.Customers;
import com.model.TicketPoolingSystem;
import com.model.Vendors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin ("http://localhost:3004")
@RequestMapping("/api/ticket-controller")
public class TicketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int vendorCount;
    private int customerCount;
    private TicketPoolingSystem ticketPool;

    @PostMapping("/configuration")
    public String Configuration(@RequestParam int totalTickets, @RequestParam int ticketReleaseRate, @RequestParam int customerRetrievalRate, @RequestParam int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;

        ticketPool = new TicketPoolingSystem(maxTicketCapacity);

        LOGGER.info("Configuration set with totalTickets: {}, ticketReleaseRate: {}, customerRetrievalRate: {}, maxTicketCapacity: {}",
                totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        return "Configuration completed successfully";
    }

    @PostMapping("/set-vendors")
    public String setVendors(@RequestParam int vendorCount) {
        this.vendorCount = vendorCount;

        LOGGER.info("Vendor count set to {}", vendorCount);

        return "Vendor Count set successfully";
    }

    @PostMapping("/set-customers")
    public String setCustomers(@RequestParam int customerCount) {
        this.customerCount = customerCount;

        LOGGER.info("Customer count set to {}", customerCount);

        return "Customer Count set successfully";
    }

    @PostMapping("/start")
    public String startTheSystem() {
        if (vendorCount > 0) {
            LOGGER.info("Starting system with {} vendors and {} customers", vendorCount, customerCount);

            for (int i = 0; i < vendorCount; i++) {
                Vendors vendor = new Vendors(ticketPool, (i + 1));
                new Thread(vendor).start();
                LOGGER.info("Vendor {} started", i + 1);
            }
            for (int i = 0; i < customerCount; i++) {
                Customers customer = new Customers(ticketPool, (i + 1));
                new Thread(customer).start();
                LOGGER.info("Customer {} started", i + 1);
            }
        } else {
            LOGGER.warn("Attempted to start the system with vendorCount = 0");
        }
        return "System started";
    }
}
