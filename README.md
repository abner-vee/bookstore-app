# Book Store REST API Application

Welcome to the Book Store REST API application! This guide will help you set up and run the application.

## Prerequisites

- Java Development Kit (JDK) 17 or later
- Apache Maven 3.6.0 or later
- MySQL database

## Setting Up the Database

1. **Create a MySQL Database:**

   Open your MySQL client and run the following command to create the `bookstore` database:

   ```sql
   CREATE DATABASE bookstore;


2. **Update Application Properties:**

Open the application.properties file in the src/main/resources directory and update the database username and password:

**properties**

```spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
```

3. **Building and Running the Application**
Navigate to the Application Root Directory:

Open a terminal or command prompt and navigate to the root directory of the application.

**Build the Application:**

Run the following command to clean and build the application:


``` 
mvn clean install 
```

**Run the Application:**

4. **Start the Spring Boot application by running:**


```
mvn spring-boot:run
```
5. **Accessing the Swagger Documentation**
Once the application is running, you can access the Swagger documentation at the following URL:

```
http://localhost:8089/api/v1/swagger-ui.html
```

**Note**

Ensure that you have updated the application.properties file with your MySQL database username and password before starting the application.
