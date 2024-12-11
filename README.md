TicketBooking System

Introduction

The TicketBooking System is a real-time event ticketing platform designed to streamline the process of browsing, booking, and managing tickets for events. This system leverages Java Spring Boot for the backend and integrates a robust user interface to ensure a seamless experience for users.

Setup Instructions

Prerequisites

To set up and run the application, ensure you have the following installed:

Java Development Kit (JDK): Version 17 or higher

Apache Maven: Version 3.8.1 or higher

Node.js and npm: Version 16 or higher (if the frontend is separate and managed with Node.js)

MySQL: For database setup

How to Build and Run the Application

Clone the Repository

git clone <repository_url>
cd TicketBooking

Configure the Database

Open the src/main/resources/application.properties file.

Update the database configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password

Build the Application

Run the Maven build command:

./mvnw clean install

Run the Application

Start the Spring Boot application:

./mvnw spring-boot:run

Usage Instructions

Configuring the System

Ensure the database is up and running with the required schema.

Load any initial data, if necessary, using scripts or manual insertion.

Starting the System

Access the application by navigating to http://localhost:8080 in your web browser after starting the backend.

UI Controls

Browse Events: View a list of available events with details.

Search Bar: Locate events by name, date, or location.

Book Tickets: Select an event and specify the number of tickets to book.

Manage Bookings: View or cancel existing bookings through the user profile.

