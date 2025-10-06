# Bajaj Finserv Health - Hiring Challenge Solution

This is just another spring boot application created as part of the Bajaj Finserv Health hiring challenge. The application automates the generation of a webhook, solves the SQL problem, and then submits a solution back to the server, all just triggered when the application is running.

---
## 📋 Features

* **Automatic Execution**: There is no need for any manual API calls even at the initiation of the entire flow; everything is triggered when the application starts.
* **Webhook Generation**: Sends a POST request with the user data to generate a unique webhook URL and an access token.
* **Dynamic Problem Solving**: Define the SQL problem to be solved based on whether the last two digits of the user registration number are even or odd.
* **Secure Submission**: Finally submits the SQL query at the end of the process to the webhook URL using `accessToken` as a JWT for authorization in the request header.

---
## 🛠️ Technology Stack

* **Java**: Version 17+
* **Spring Boot**: Framework for building the application.
* **Maven**: Dependency management and build tool.
* **Spring Web**: Used for `RestTemplate` to make HTTP requests.
* **Lombok**: Reduces boilerplate code for model classes.

---
## ⚙️ Configuration

Before running the application, you must update the placeholder values with your personal details.

1.  Navigate to the file: `src/main/java/com/bajajfinserv/healthchallenge/ChallengeRunner.java`
2.  Modify these lines with your information:
    ```java
    // !!! IMPORTANT: REPLACE THESE VALUES WITH YOUR DETAILS !!!
    WebhookRequest requestBody = new WebhookRequest(
        "John Doe",        // <-- Change this to your name
        "REG12347",        // <-- Change this to your registration number
        "john@example.com" // <-- Change this to your email
    );
    ```

---
## 🚀 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/shishpal0666/BajajFinserv-API-Assignment.git](https://github.com/shishpal0666/BajajFinserv-API-Assignment.git)
    ```
2.  **Navigate into the directory:**
    ```bash
    cd BajajFinserv-API-Assignment
    ```
3.  **Build the project using the Maven wrapper:**
    ```bash
    ./mvnw clean package
    ```
4.  **Execute the final JAR file:**
    ```bash
    java -jar target/health-challenge-0.0.1-SNAPSHOT.jar
    ```
5.  Observe the console output to see the process unfold, ending with the final success message.
