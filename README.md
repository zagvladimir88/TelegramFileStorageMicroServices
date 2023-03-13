# TelegramFileStorageMicroServices
Telegram-based file exchange service

# Description
This project is a file exchange service that
uses the Telegram messenger as an interface for
file uploads. A user can register, save photos 
or documents and receive a download link for 
the file, which they can share with other users.
The project is built on a microservice
architecture, using RabbitMQ message broker 
and Postgresql database. Microservices are
written with Spring Boot and Spring JPA, and
include registration, email confirmation, and
account activation.

# Project structure
The project consists of the following microservices:

* Dispatcher - a service that receives file upload requests from clients, checks user authentication and forwards the request to the RabbitMQ queue for further processing;
* Node - a service that receives file upload requests from the RabbitMQ queue, saves the file to disk, and sends the download link to the client;
* Mail service - a service responsible for sending email messages with registration confirmation and account activation;
* RESTful service - a service that provides an API for working with user data and authentication.


# Technologies Used
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)
![Telegram](https://img.shields.io/badge/Telegram-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white)

*  Spring Boot
*  Spring Data JPA
*  Telegram-API
*  PostgreSQL 14
*  RabbitMQ
*  Flyway: Version control tool for database schemas.
*  JUnit: For unit testing.
*  Spring Boot Mail: Spring module for sending emails.

# Requirements
To run the project, you need to install the following components:

JDK 17 or higher;
Apache Maven 3.6.0 or higher;
Docker 20.10.0 or higher;
RabbitMQ 3.7.0 or higher;
Postgresql 9.6 or higher.

