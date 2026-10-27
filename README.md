
# Blood Donation Management System

A Spring Boot REST API for managing blood donors, nurses, certifications, donation beds, and blood donations.

## Technologies
- Java 17
- Spring Boot 4.0.9
- Spring Data JPA
- PostgreSQL
- Maven

## Database
Database name: blood_donation_db

## Running the Application
1. Create a PostgreSQL database named blood_donation_db.
2. Configure the PostgreSQL username and DB_PASSWORD environment variable.
3. Run the application using:
   ./mvnw spring-boot:run

## Part A
Implemented five entities and their CRUD operations:
- Certification
- DonationBed
- Nurse
- Donor
- Donation
