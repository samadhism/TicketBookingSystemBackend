Ticket Booking System

Introduction

The Ticket Booking System is a real-time platform designed to manage and streamline the ticketing process for events. Built with a Spring Boot backend and a React.js frontend, the system supports secure user authentication, seamless ticket purchasing, and administrative controls for event management. Its interactive UI and robust functionality make it an ideal solution for event organizers and attendees alike.

Setup Instructions

Prerequisites

To set up and run the application, ensure you have the following installed:

Java Development Kit (JDK): Version 11 or higher

Node.js and npm: Latest LTS version

MySQL Database: Version 8.0 or higher

Maven: Version 3.6.3 or higher

Backend Setup

Navigate to the backend directory of the project:

cd backend

Configure the database connection in the application.properties file:

spring.datasource.url=jdbc:mysql://localhost:3306/ticket_booking
spring.datasource.username=your_username
spring.datasource.password=your_password

Build the backend application using Maven:

mvn clean install

Run the Spring Boot application:

mvn spring-boot:run

Frontend Setup

Navigate to the frontend directory of the project:

cd frontend

Install dependencies:

npm install

Start the React.js application:

npm start

Access the application at http://localhost:3000.

Usage Instructions

Configuring the System

Ensure the backend and frontend are running.

Create initial data by running the provided SQL script (initial_data.sql) in your MySQL database.

Open the application in your browser and log in as an administrator to configure events and ticket types.

UI Controls

Login Page:

Enter your credentials to access the platform.

Dashboard:

Admin users can manage events, view sales data, and monitor user activity.

Regular users can browse available events and purchase tickets.

Event Listing:

Displays a list of upcoming events.

Clicking on an event shows detailed information, including ticket availability.

Ticket Purchase:

Select the number of tickets and proceed to payment.

User Profile:

View and manage your ticket purchases and account settings.
