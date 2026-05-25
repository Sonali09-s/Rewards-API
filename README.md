# Rewards API (Spring Boot)

[![Java Version](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://openjdk.org/projects/jdk17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)

## Overview

Rewards API is a production-ready Spring Boot REST application built to calculate reward points earned by customers based on their retail purchase transactions. 

The application aggregates data to calculate:
* **Monthly reward points** per customer.
* **Total accumulated reward points** per customer.

### Key Architectural Pillars
* **Layered Architecture:** Clear separation of concerns (Controller ➡️ Service ➡️ Repository).
* **Robust Error Handling:** Centralized global exception interception using `@RestControllerAdvice`.
* **Comprehensive Testing:** High test coverage featuring both localized Unit tests and end-to-end Integration tests.

---

##  Reward Calculation Rules

Reward points are allocated based on a tiered threshold system per transaction:
* **2 points** for every dollar spent above **$100**.
* **1 point** for every dollar spent between **$50** and **$100**.

### Scenario Example
For a transaction amount of **$120**:
* Points for the tier above $100:  
  $$2 \times (120 - 100) = 40 \text{ points}$$
* Points for the tier between $50 and $100:  
  $$1 \times (100 - 50) = 50 \text{ points}$$
* **Total Earned** = **90 points**

---

##  Technologies & Tooling

* **Language:** Java 17
* **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Database:** H2 In-Memory Database
* **Build Tool:** Maven
* **Testing:** JUnit 5, Mockito, MockMvc

---

##  Project Structure

```text
src/main/java/com/example/rewards
│
├── controller
│   └── RewardController.java
├── service
│   └── RewardService.java
├── repository
│   └── TransactionRepository.java
├── entity
│   └── Transaction.java
├── dto
│   ├── MonthlyReward.java
│   └── RewardResponse.java
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
└── RewardsApiApplication.java
```

**API Specification**
**Get Reward Details by Customer ID**
**Endpoint:** /api/rewards/{customerId}

**Method:** GET

**Content-Type:** application/json

**Example Request**
```http
GET /api/rewards/1 HTTP/1.1
Host: localhost:8080
```

Example Response (200 OK)
```
{
  "customerId": 1,
  "customerName": "John Doe",
  "monthlyRewards": [
    {
      "month": "JANUARY",
      "points": 115
    },
    {
      "month": "FEBRUARY",
      "points": 250
    }
  ],
  "totalRewards": 365
}
```
## Database & Sample Data

### H2 Console Configuration
The application leverages an embedded H2 database initialized with sample transaction patterns via `src/main/resources/data.sql` on startup.

* **Console URL:** http://localhost:8080/h2-console
* **JDBC URL:** jdbc:h2:mem:rewardsdb
* **Username:** sa
* **Password:** (Leave blank)

### Verification Query
```sql
SELECT * FROM TRANSACTION;
```
**Testing Strategy**
The test suite ensures application reliability across edge cases (e.g., negative amounts, missing IDs, null values).

**Unit Testing (JUnit 5 & Mockito)**
Focuses on isolating core algorithmic logic and validating calculations in RewardServiceTest.
```bash
mvn test
```
**Integration Testing (@SpringBootTest & MockMvc)**
**Verifies the full request-response lifecycle, routing, HTTP status codes, and serialization accuracy in RewardControllerTest.**

**Getting Started**
**Prerequisites**

* **JDK 17 or higher**
* **Maven 3.x Installed**

**Setup Instructions**
**1. Clone the repository:**

```bash
**git clone <your-github-repository-url>**
**cd rewards-api**
```
**2. Build and package the application:**

```bash
**mvn clean install**
```
**3. Run the application:**

```bash
**mvn spring-boot:run**
```
**The server will start up locally on http://localhost:8080.**

**Roadmap / Future Improvements**

* **Add Swagger/OpenAPI 3 documentation support for easier API interactive exploration.**
* **Externalize database configuration to support persistent profiles like PostgreSQL/MySQL.**
* **Add containerization layer via a Dockerfile.**
* **Introduce Request Validation rules (@Valid, @NotNull, @Min).**
* **Implement security contexts via Spring Security & JWT.**

**Configuration Files Location**

```text
**src/main/resources**
**├── application.yml**
**└── data.sql**

