# Budget Tracker

Budget Tracker is a Spring Boot application designed to help users manage their financial transactions and categorize them effectively.

## Features

- **Category Management**: Create and manage categories for transactions.
- **Transaction Tracking**: Record and track financial transactions with details like amount, date, and description.
- **Database Schema Management**: Uses Liquibase for database versioning and schema management.

## Technologies Used

- **Java** and **Kotlin**
- **Spring Boot**
- **Gradle** for build automation
- **Liquibase** for database migrations
- **PostgreSQL** as the database

## Getting Started

### Prerequisites

- Java 21 or higher
- Gradle 8.13 or higher
- PostgreSQL database

### Configuration

Update the `src/main/resources/application.properties` file with the necessary configurations:

```ini
spring.application.name=budget-tracker
server.port=8080
