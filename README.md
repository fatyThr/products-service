# Products Service - E-commerce Microservice

This repository contains the Products Service, a microservice built for the e-commerce API using hexagonal architecture, Spring Boot, and Java 11.

## Table of Contents

- [Description](#description)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Usage](#usage)
- [API Documentation](#api-documentation)

## Description

The Products Service is a microservice designed to handle product-related functionalities for an e-commerce platform. It follows a hexagonal architecture pattern, which promotes separation of concerns and maintainability. The service is built using Spring Boot and Java 11, providing a robust foundation for the e-commerce API.

## Getting Started

### Prerequisites

To run the Products Service, make sure you have the following installed on your system:
- Java Development Kit (JDK) 11
- Maven 
- H2 Database

#### Database Setup

-Create the H2 database:
CREATE DATABASE testdb;
-Create the "product" table :
CREATE TABLE product(
    id UUID DEFAULT UUID() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
	purchase DECIMAL(10,2) NOT NULL,
    creation_date DATETIME,
    image_path VARCHAR(255)
);
### Installation

1.	Clone the repository:
git clone https://github.com/fatyThr/products-service.git
2.  Build the project using Maven:
> cd products-service
> mvn clean install

### Configuration

To start the Products Service, use the following command:
> java -jar products-service.jar
 
 
## API Documentation

For detailed information on available APIs and endpoints, refer to the API documentation at docs/api.md.

Swagger UI Documentation: http://localhost:8080/swagger-ui/

 


