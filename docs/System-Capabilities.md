# System Capability Map
## Purpose

This document defines the high-level system capabilities of HRIS Lite.

A **system capability** describes what the system is able to accomplish from a business perspective, rather than listing individual API endpoints.

Status legend:

- 🟢 Done
- 🟡 Partial
- 🔴 Not Implemented

## 1. Organization Management

**Goal:** Enable HR to define and manage the organization's structure.

**User:** HR Admin

### Core capabilities

- Create company
- View company
- Update company
- Disable company
- Create department
- View department
- Update department
- Disable department

### Expected outcome

> HR can define the organizational structure of the company inside HRIS.

**Status:** 🟢 Done

---
## 2. Person Management

**Goal:** Enable HR to manage the basic identity and personal information of a person.

**User:** HR Admin

### Core capabilities

- Create person
- View person
- Rename person
- Change birthday
- Update gender
- Update profile photo
- Manage person contact information 
- Disable person

### Expected outcome

> HR can create and maintain a person's basic identity information.

**Status:** 🟢 Done

---
## 3. Employee Management

**Goal:** Enable HR to manage a person as an employee of the company.

**User:** HR Admin

### Core capabilities

- Create employee
- Generate employee number
- View employee
- Update employee
- Deactivate employee

### Expected outcome

> HR can manage a person as an employee within the company.

**Status:** 🟢 Done

---
## 4. Employment Management

**Goal:** Manage the employment relationship between an employee and the company.

**User:** HR Admin

### Core capabilities

- Define employment type
- Define employment start date
- Define employment end date
- Manage employment status
- Manage contract information 🔴
- View employment history

### Example employment types

- Permanent
- Contract
- Probation
- Internship

### Expected outcome

> HR can understand the employment relationship and current employment status of an employee.

**Status:** 🟡 Partial

---
## 5. Position Management

**Goal:** Define and manage positions available within the organization.

**User:** HR Admin

### Core capabilities

- Create position
- View position
- Update position
- Disable position
- Define position hierarchy 🔴

### Example positions

- Software Engineer
- Senior Software Engineer
- HR Manager
- Finance Staff

### Expected outcome

> HR can define the positions that exist within the organization.

**Status:** 🟡 Partial

---
## 6. Employee Assignment

**Goal:** Assign an employee to a specific part of the organization.

**User:** HR Admin

### Core capabilities

- Assign employee to company
- Assign employee to department
- Assign employee to position
- Assign employee to work location
- Assign employee to manager
- Change employee assignment
- View assignment history

### Example

```text
Employee : Febri
Company  : ABC
Department: Engineering
Position : Software Engineer
Location : Jakarta
Manager  : Budi
```

### Expected outcome

> HR can place an employee into the organization's structure.

**Status:** 🔴 Not Implemented

---

## 7. Employee Lifecycle

**Goal:** Manage important changes throughout an employee's employment lifecycle.

**User:** HR Admin

### Core lifecycle events

- Join
- Transfer
- Promotion
- Employment change
- Resignation
- Termination

### Example lifecycle

```text
JOIN
  ↓
ACTIVE
  ↓
TRANSFER / PROMOTION
  ↓
ACTIVE
  ↓
RESIGNATION / TERMINATION
  ↓
INACTIVE
```

### Expected outcome

> HR can manage an employee's journey from joining the company until leaving the company.

**Status:** 🔴 Not Implemented

---

## 8. User & Access Management

**Goal:** Manage system users, roles, and permissions.

**User:** HR Admin

### Core capabilities

- Create user account
- Link user to employee
- Assign role
- Manage permissions
- Activate/deactivate user
- Control access to system resources

### Example roles

```text
HR_ADMIN
EMPLOYEE
MANAGER
SYSTEM_ADMIN
```

### Example permissions

```text
person:create
person:view
person:update

employee:create
employee:view
employee:update

employee:assign
employee:terminate
```

### Expected outcome

> The system can distinguish users and control what each user is allowed to do.

**Status:** 🔴 Not Implemented

---

## 9. Employee Document Management

**Goal:** Store and manage documents associated with employees.

**User:** HR Admin

### Core capabilities

- Upload document
- View document
- Download document
- Archive/delete document
- Define document type
- Define document expiry date
- View document history

### Example documents

- ID Card
- Tax ID
- Employment Contract
- Diploma
- Certificate
- Other employee documents

### Expected outcome

> HR can store and access documents associated with an employee.

**Status:** 🔴 Not Implemented

---

## 10. HR Master Data

**Goal:** Manage reusable HR reference data used throughout the system.

**User:** HR Admin

### Example master data

```text
Employment Type
├── Permanent
├── Contract
└── Internship

Employee Status
├── ACTIVE
├── INACTIVE
├── RESIGNED
└── TERMINATED

Document Type
├── ID_CARD
├── TAX_ID
├── CONTRACT
└── CERTIFICATE
```

### Core capabilities

- Create master data
- View master data
- Update master data
- Activate/deactivate master data
- Group master data by type

### Expected outcome

> HRIS has consistent reference data that can be reused across different capabilities.

**Status:** 🔴 Not Implemented

---

# Capability Dependency Map

The capabilities are not completely independent. A practical implementation order is:

```text
                    HR Master Data
                          │
          ┌───────────────┴───────────────┐
          ▼                               ▼
 Organization Management           Person Management
          │                               │
          ▼                               ▼
 Position Management               Employee Management
          │                               │
          └───────────────┬───────────────┘
                          ▼
                Employment Management
                          │
                          ▼
                 Employee Assignment
                          │
                          ▼
                 Employee Lifecycle
                          │
              ┌───────────┴───────────┐
              ▼                       ▼
     Document Management       User & Access
```

---

# Recommended First Business Flow

The first important end-to-end capability should be:

> **HR Admin can create an employee and place that employee into the organization.**

The flow should eventually look like:

```text
Create Person
      ↓
Create Employee
      ↓
Select Company
      ↓
Select Department
      ↓
Select Position
      ↓
Set Employment
      ↓
Assign Employee
      ↓
Employee becomes ACTIVE
```

When this flow can be completed end-to-end, HRIS Lite has its first meaningful business capability rather than simply having a collection of CRUD APIs.

---

# Development Principle

Use the following hierarchy when planning development:

```text
System Capability
        ↓
Business Flow
        ↓
Use Case
        ↓
API / Domain Logic
        ↓
Test
        ↓
API Documentation
        ↓
Feature Catalog
```

The **Capability Map** answers:

> What can HRIS Lite do?

The **Business Flow** answers:

> What can the user accomplish from beginning to end?

The **API Documentation** answers:

> How can another system or client interact with it?

The **Feature Catalog** answers:

> What has already been implemented?

Therefore, API documentation and feature catalogs should not be the primary source for deciding what to build next. The capability and business-flow view should drive development priorities.
