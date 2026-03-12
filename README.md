# Financial Transaction & Budget Management Backend System

A **Java-based backend system** for managing personal financial transactions, accounts, and monthly budgets.  
The system enables users to track income and expenses, organize transactions into categories, enforce financial constraints, and generate analytical summaries of spending behavior.

This project demonstrates backend development concepts including **relational database design, transactional integrity, financial data consistency, and analytics queries** using **JDBC with MySQL**.

---

# Tech Stack

## Backend
- Java (JDK 17)
- JDBC

## Database
- MySQL

## Development Tools
- IntelliJ IDEA
- Git & GitHub

---

# System Overview

The system models a **realistic financial management backend** where users maintain multiple accounts and track financial activity through categorized transactions.

Core capabilities include:

- Managing user accounts
- Recording income and expense transactions
- Tracking monthly category budgets
- Maintaining financial integrity through transactions
- Generating analytical insights from stored data
- Maintaining audit logs for financial actions

---

# Core Features

## User Management

Users can register and authenticate into the system.

**Capabilities**
- Register new users
- Secure login authentication
- Unique username enforcement

---

## Account Management

Each user can maintain **multiple financial accounts**, such as:

- Savings account
- Salary account
- Wallet

**Features**

- Account creation
- Balance tracking
- Account-specific transaction history

---

## Transaction Management

The system allows users to record financial transactions.

**Supported operations**

- Add income transactions
- Add expense transactions
- Categorize transactions
- Maintain transaction history

Each transaction records:

- Account used
- Category
- Amount
- Description
- Timestamp

---

## Category System

Transactions are organized using categories such as:

- Food
- Transport
- Rent
- Salary
- Entertainment
- Investment

Each category is linked to:

- A user
- A transaction type (`INCOME` / `EXPENSE`)

This allows **structured financial analysis.**

---

## Monthly Budget Tracking

Users can set **monthly spending limits** for categories.

| Category | Monthly Limit |
|--------|--------|
| Food | ₹5000 |
| Transport | ₹2000 |

The system tracks spending against these budgets.

---

# Financial Analytics

The system provides analytical insights including:

## Category Spending Summary

Shows total spending per category.

Example:
Month 1 → ₹10500
Month 2 → ₹9200


---

## Category Breakdown

Provides detailed expense distribution across categories.

---

## Transaction History

Retrieve transaction records based on:

- Account ID
- Category
- Date range

---

# Transaction Integrity (ACID Handling)

Financial systems must **never corrupt balances**.

This project uses **JDBC transaction management**:

setAutoCommit(false)
commit()
rollback()


### Example transaction flow

1. Insert transaction
2. Update account balance
3. Write audit log

If any step fails → **full rollback occurs**.

---

# Financial Integrity Rules

The system enforces:

- No negative account balances
- Foreign key constraints
- Mandatory fields
- Unique username constraint

These ensure **data reliability and consistency.**

---

# Audit Logging

Every financial operation is recorded.

**Logged actions include**

- Transaction creation
- Transaction updates
- Transaction deletion

Audit logs store:

- User ID
- Action type
- Entity affected
- Timestamp

This simulates **real-world financial system traceability.**

---

# Database Schema

The system uses a **normalized relational schema**.

Main tables:

- `users`
- `accounts`
- `transactions`
- `categories`
- `budgets`
- `audit_logs`

### Relationships

- User → Accounts
- User → Categories
- Account → Transactions
- Category → Transactions
- User → Budgets

Foreign key constraints maintain **referential integrity**.

---

# Project Structure

### Example transaction flow

1. Insert transaction
2. Update account balance
3. Write audit log

If any step fails → **full rollback occurs**.

---

# Financial Integrity Rules

The system enforces:

- No negative account balances
- Foreign key constraints
- Mandatory fields
- Unique username constraint

These ensure **data reliability and consistency.**

---

# Audit Logging

Every financial operation is recorded.

**Logged actions include**

- Transaction creation
- Transaction updates
- Transaction deletion

Audit logs store:

- User ID
- Action type
- Entity affected
- Timestamp

This simulates **real-world financial system traceability.**

---

# Database Schema

The system uses a **normalized relational schema**.

Main tables:

- `users`
- `accounts`
- `transactions`
- `categories`
- `budgets`
- `audit_logs`

### Relationships

- User → Accounts
- User → Categories
- Account → Transactions
- Category → Transactions
- User → Budgets

Foreign key constraints maintain **referential integrity**.

---


Author: Ramm Lakshmanan


GitHub:
https://github.com/Ramm-Lakshmanan
