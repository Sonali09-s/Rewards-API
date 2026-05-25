Overview
This project is a Spring Boot-based RESTful API that calculates reward points earned by customers based on their transactions over a three-month period

The rewards system follows these rules:
-- 2 points for every dollar spent above $100
-- 1 point for every dollar spent between $50 and $100
-- No points for amounts below $50

# Rewards API

Spring Boot REST API for calculating customer reward points.

## Run Application

mvn clean install
mvn spring-boot:run

## Endpoint

GET /api/rewards/{customerId}
