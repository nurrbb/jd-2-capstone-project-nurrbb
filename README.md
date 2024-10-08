# Airline Ticketing System Backend Service Application

## Technologies Used
- **Java 17**
- **Maven**
- **Spring Boot 3**
- **MySQL Database**
- **Restful Web Service (JSON)**

## Project Overview
The Airline Ticketing System is a backend service application developed to manage essential functions for airline companies, airports, routes, and flight ticketing. It is designed using Spring Boot and MySQL for robust data management and RESTful APIs for efficient communication.

## Features
- **Airline Management:**  
  - Add new airline companies.
  - Search for existing airline companies.

- **Airport Management:**  
  - Add new airports.
  - Search for existing airports.

- **Route Management:**  
  - Add new routes.
  - Search for existing routes.

- **Flight Management:**  
  - Define new flights for airline companies.
  - Search for existing flights.

- **Ticket Purchasing:**  
  - Purchase tickets with credit card information masking (e.g., "4221161122330005" -> "422116******0005").
  - Accepts credit card numbers with various delimiters (e.g., "4221-1611-2233-0005", "4221,1611,2233,0005") and masks them in the expected format.
  - Search for tickets using the ticket number.
  - Cancel tickets using the ticket number.

- **Soft Delete:**  
  Soft delete is implemented for operations that require deletion, ensuring data integrity.

- **Global Exception Handling:**  
  Centralized handling of exceptions to maintain consistency in error responses.

- **API Responses:**  
  Standardized response structure for all API endpoints.

## Testing
- Unit tests are written for all public methods, ensuring reliability and correctness of the application's logic.
- Test coverage is maintained at a minimum of 60%.

## Project Structure
The project is hosted on GitHub and developed as a backend-only service, focusing on API functionalities. It can be tested using tools like Postman.

Feel free to clone the repository and explore the codebase!
