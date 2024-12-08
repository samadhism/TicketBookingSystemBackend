package CLI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class TicketSelling {
    private static Configuration  configuration = new Configuration();
    private static final String CONFIG_FILE_PATH = "configuration.json"; // File path for the JSON file

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        System.out.println("___Welcome to CLI system___");

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add configuration");
            System.out.println("2. Save configuration");
            System.out.println("3. View configuration");
            System.out.println("4. Start the Simulation");
            System.out.println("5. Exit");

            System.out.print("Your choice: ");

            try {
                int option = input.nextInt();

                switch (option) {
                    case 1:
                        addConfiguration(input);
                        break;
                    case 2:
                        saveConfiguration();
                        break;
                    case 3:
                        viewConfiguration();
                        break;
                    case 4:
                        startSimulation();
                    case 5:
                        exit = true;
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid number (1-4).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // Method to add configuration
    private static void addConfiguration(Scanner input) {
        try {
            configuration.setTotalTickets(getPositiveInt(input, "Enter total tickets: "));
            configuration.setTicketReleaseRate(getPositiveInt(input, "Enter ticket release rate (in seconds): "));
            configuration.setCustomerRetrievalRate(getPositiveInt(input, "Enter customer retrieval rate (in seconds): "));
            configuration.setMaxTicketCapacity(getPositiveInt(input, "Enter max ticket capacity: "));

            System.out.println("Configuration added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            input.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding configuration: " + e.getMessage());
        }
    }

    // Method to save configuration as a JSON file
    private static void saveConfiguration() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create Gson instance with pretty printing
        try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH)) {
            gson.toJson(configuration, writer); // Serialize configuration object to JSON and write to file
            System.out.println("Configuration saved successfully in JSON format at " + CONFIG_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error saving configuration to file: " + e.getMessage());
        }
    }

    // Method to view configuration
    private static void viewConfiguration(){
        configuration.displayConfiguration();
    }


    //Method to start the simulation
    private static void startSimulation(){
        String vendorName;
        String customerName;

        TicketPool ticketPool = new TicketPool(configuration.getMaxTicketCapacity());
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            vendorName = "Vendor" + (i+1);
            Vendor vendor = new Vendor(1,10, 2000, ticketPool, vendorName);
            Thread thread = new Thread(vendor);
//            threads.add(thread);
            thread.start();
        }

        for (int i = 0; i < 3; i++) {
            customerName = "Customer" + (i+1);
            Customer c1 = new Customer(1,1000,ticketPool,customerName);
            Thread thread = new Thread(c1);
//            threads.add(thread);
            thread.start();
        }

    }


    // Method for input validation
    private static int getPositiveInt(Scanner input, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = input.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.nextLine(); // Clear invalid input
            }
        }
    }
}

