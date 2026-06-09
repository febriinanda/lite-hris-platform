# Lite HRIS Platform
Enterprise-grade HRIS platform built with Spring Boot, MySQL, Redis, Kafka and OAuth2 Authentication Server.

## Project Scope
This project focuses on core HRIS functionalities.

Out of Scope:
- Payroll Processing
- Tax Calculation
- BPJS Integration
- Recruitment Module
- Performance Management

## Key Technical Highlights:
- Clean Architecture
- DDD-inspired module separation
- OAuth2 Authorization Server
- JWT Authentication
- Event-driven communication
- Audit Logging
- Flyway Database Migration
- Dockerized Deployment
- CI/CD Pipeline

## Architecture
### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Redis
- Kafka
- Flyway

## Planned Technical Modules

### Audit Logging ⚪
- Entity change tracking
- User activity history

### Notification Service ⚪
- Email notification
- In-app notification

### File Management ⚪
- Employee document storage
- Profile photo storage

### Security ⚪
- Role Based Access Control (RBAC)
- OAuth2 Authorization Server
- JWT Authentication

## Features
### Employee Management 🟡
- Employee master data 🟢
- Employment status 🟢
- Position and department assignment 🟢
- Company and office assignment 🟢
- Employee document management ⚪
- Employee profile photo ⚪
- Employee registration number 🟢

### Organization Management 🟡
- Company 🟢
- Office 🟢
- Department 🟢
- Position 🟢
- Reporting structure

### Leave Management ⚪
- Annual leave
- Sick leave
- Special leave
- Leave balance tracking
- Leave approval workflow

### Employee Self Service (ESS) 🟡
- View profile 🟢
- Update personal information 🟢
- Submit leave request
- View attendance history

### Approval Workflow ⚪
- Multi level approval
- Approval history
- Approval notifications

### Dashboard ⚪
- Employee statistics
- Attendance summary
- Leave summary

#### Legends:
🟢 Done  
🟡 In Progress  
⚪ Planned  
🔴 Blocked  
🔵 Under Review
