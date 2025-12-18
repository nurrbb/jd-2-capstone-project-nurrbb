# Airline Ticketing System

A comprehensive backend service for managing airline operations, including airlines, airports, routes, flights, and ticket reservations. Built with Spring Boot, this system provides RESTful APIs for airline companies to manage their operations efficiently.

## 🚀 Features

### Core Management
- **Airline Management**: Create and search airline companies
- **Airport Management**: Add and search airports worldwide
- **Route Management**: Define flight routes between airports with average duration
- **Flight Management**: Schedule flights with detailed timing and pricing information

### Advanced Ticketing System
- **Seat Reservation**: Real-time seat availability checking and assignment
- **Flight Scheduling**: Departure/arrival times with automatic duration calculation
- **Dynamic Pricing**: Base pricing for flights with ticket-specific pricing
- **Secure Payment**: Credit card validation and masking for ticket purchases
- **Ticket Operations**: Purchase, search, and cancel tickets

### Data Integrity & Validation
- **Input Validation**: Comprehensive validation using Jakarta Validation API
- **Custom Exceptions**: Specific exception handling for business logic errors
- **Soft Delete**: Safe deletion operations preserving data integrity
- **Global Exception Handling**: Centralized error management

## 🛠️ Technology Stack

- **Java**: 21 (LTS)
- **Framework**: Spring Boot 3.1.2
- **Build Tool**: Maven
- **Database**: MySQL (Production) / H2 (Testing)
- **ORM**: JPA/Hibernate
- **Validation**: Jakarta Validation API
- **Documentation**: SpringDoc OpenAPI
- **Boilerplate**: Lombok 1.18.30

## 📁 Project Structure

```
src/
├── main/
│   ├── java/io/upschool/
│   │   ├── CapstoneProjectApplication.java
│   │   ├── config/
│   │   │   └── JsonConfig.java
│   │   ├── controller/
│   │   │   ├── AirlineController.java
│   │   │   ├── AirportController.java
│   │   │   ├── FlightController.java
│   │   │   ├── RouteController.java
│   │   │   └── TicketController.java
│   │   ├── dto/
│   │   │   ├── Airlinedto.java
│   │   │   ├── AirlineSaveRequest.java
│   │   │   ├── AirlineSaveResponse.java
│   │   │   ├── AirportSaveRequest.java
│   │   │   ├── AirportSaveResponse.java
│   │   │   ├── BaseResponse.java
│   │   │   ├── FlightProjection.java
│   │   │   ├── FlightSaveRequest.java
│   │   │   ├── FlightSaveResponse.java
│   │   │   ├── RouteProjection.java
│   │   │   ├── RouteSaveRequest.java
│   │   │   ├── RouteProjection.java
│   │   │   ├── RouteSaveRequest.java
│   │   │   ├── RouteSaveResponse.java
│   │   │   ├── TicketSaveRequest.java
│   │   │   └── TicketSaveResponse.java
│   │   ├── entity/
│   │   │   ├── Airline.java
│   │   │   ├── Airport.java
│   │   │   ├── Flight.java
│   │   │   ├── Route.java
│   │   │   └── Ticket.java
│   │   ├── exception/
│   │   │   ├── AirlineAlreadySavedException.java
│   │   │   ├── AirlineNotFoundException.java
│   │   │   ├── AirportAlreadySavedException.java
│   │   │   ├── AirportNotFoundException.java
│   │   │   ├── FlightNotFoundException.java
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── InsufficientSeatsException.java
│   │   │   ├── InvalidCreditCardNumberException.java
│   │   │   ├── InvalidRouteException.java
│   │   │   ├── RouteAlreadySavedException.java
│   │   │   ├── RouteNotFoundException.java
│   │   │   ├── TicketAlreadySavedException.java
│   │   │   └── TicketNotFoundException.java
│   │   ├── repository/
│   │   │   ├── AirlineRepository.java
│   │   │   ├── AirportRepository.java
│   │   │   ├── FlightRepository.java
│   │   │   ├── RouteRepository.java
│   │   │   └── TicketRepository.java
│   │   └── service/
│   │       ├── AirlineService.java
│   │       ├── AirportService.java
│   │       ├── FlightService.java
│   │       ├── RouteService.java
│   │       └── TicketService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/io/upschool/
        └── CapstoneProjectApplicationTests.java
```

## 🗄️ Database Schema

### Airline
- `id`: Primary Key
- `name`: Airline name (unique)
- `country`: Country of origin
- `iataCode`: IATA code (unique)
- `icaoCode`: ICAO code (unique)

### Airport
- `id`: Primary Key
- `name`: Airport name
- `city`: City location
- `country`: Country location
- `iataCode`: IATA code (unique)
- `icaoCode`: ICAO code (unique)

### Route
- `id`: Primary Key
- `departureAirport`: Foreign Key to Airport
- `arrivalAirport`: Foreign Key to Airport
- `averageDurationMinutes`: Average flight duration

### Flight
- `id`: Primary Key
- `airline`: Foreign Key to Airline
- `route`: Foreign Key to Route
- `flightNumber`: Unique flight identifier
- `departureTime`: Scheduled departure time
- `arrivalTime`: Scheduled arrival time
- `durationMinutes`: Calculated flight duration
- `totalSeats`: Total number of seats
- `availableSeats`: Available seats for booking
- `basePrice`: Base ticket price

### Ticket
- `id`: Primary Key
- `flight`: Foreign Key to Flight
- `passengerName`: Passenger full name
- `passengerEmail`: Passenger email
- `seatNumber`: Assigned seat number
- `price`: Final ticket price
- `creditCardNumber`: Masked credit card number
- `ticketNumber`: Unique ticket identifier
- `isActive`: Soft delete flag

## 🔧 Setup & Installation

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+ (for production)

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd AirlineTicketingSystem
   ```

2. **Configure Database**
   - Create a MySQL database named `airline_ticketing`
   - Update `src/main/resources/application.properties` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/airline_ticketing
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### Airline Management
- `POST /api/airlines` - Create new airline
- `GET /api/airlines` - Get all airlines
- `GET /api/airlines/{id}` - Get airline by ID

### Airport Management
- `POST /api/airports` - Create new airport
- `GET /api/airports` - Get all airports
- `GET /api/airports/{id}` - Get airport by ID

### Route Management
- `POST /api/routes` - Create new route
- `GET /api/routes` - Get all routes
- `GET /api/routes/{id}` - Get route by ID

### Flight Management
- `POST /api/flights` - Create new flight
- `GET /api/flights` - Get all flights
- `GET /api/flights/{id}` - Get flight by ID

### Ticket Management
- `POST /api/tickets` - Purchase ticket
- `GET /api/tickets` - Get all tickets
- `GET /api/tickets/{id}` - Get ticket by ID
- `GET /api/tickets/search/{ticketNumber}` - Search ticket by number
- `DELETE /api/tickets/{id}` - Cancel ticket (soft delete)

## 🧪 Testing

### Run Tests
```bash
./mvnw test
```

### Test Configuration
- Uses H2 in-memory database for isolated testing
- Tests cover all public service methods
- Minimum test coverage: 60%

## 🔒 Validation & Business Rules

### Input Validation
- Airline/Airport codes: Must be unique, follow IATA/ICAO standards
- Email format validation for passengers
- Credit card number validation and masking
- Seat number format validation
- Required fields validation with custom error messages

### Business Logic
- **Seat Availability**: Prevents overbooking by checking available seats
- **Route Validation**: Ensures departure and arrival airports are different
- **Flight Duration**: Automatically calculated from departure/arrival times
- **Pricing**: Base price from flight, can be adjusted per ticket
- **Duplicate Prevention**: Unique constraints on critical fields

## 📚 API Documentation

Once the application is running, access the Swagger UI documentation at:
```
http://localhost:8080/swagger-ui.html
```

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

