Airline Ticketing System Backend Service Application

Technologies Used:

Java 17
Maven
Spring Boot 3
MySQL Database
Restful Web Service (JSON)
Project Overview: The Airline Ticketing System is a backend service application developed to manage essential functions for airline companies, airports, routes, and flight ticketing. It is designed using Spring Boot for rapid development, with a focus on scalability and ease of use. The application enables users to efficiently manage and search through airline companies, airports, routes, and flight details.

Key Features:

Airline Company Management: Allows adding new airline companies and searching for existing ones.

Airport Management: Users can add new airports and search through the list of airports.

Route Management: Enables users to create and search for routes between different airports.

Flight Definition for Airlines: Users can define flights for specific airlines and search through flight details.

Ticket Purchase and Management:

Users can purchase tickets while ensuring the security of sensitive information.
During the purchase process, credit card details are masked  to protect customer data.
The application supports various formats of credit card inputs  and ensures they are masked uniformly.
Users can search for tickets using ticket numbers and cancel tickets if needed.
Soft Deletion for Deletion Processes: For all delete operations, a soft delete approach is applied to maintain data integrity and allow for recovery if necessary.

Global Exception Handling: A comprehensive global exception handling mechanism is implemented to provide consistent error management throughout the application.

Consistent API Responses: API responses follow a standardized format, ensuring a uniform experience across all endpoints.
