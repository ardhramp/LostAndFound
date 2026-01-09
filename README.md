# Lost & Found Management System

## Overview
The Lost & Found Management System is a web-based application built using Java Servlets, JSP, and HTML. It allows users to report lost items, claim found items, and manage item records efficiently. The system is designed for easy tracking and management of lost and found items in an organization or community.

---

## Features
- **User Registration and Login:** Users can create an account and log in securely.
- **Add Lost Items:** Registered users can report lost items.
- **Claim Items:** Users can claim items found in the system.
- **View Items:** Users can view all lost and found items.
- **Admin Dashboard:** Admins can manage user accounts and items (if implemented).

---
## Technologies Used
- Java (Servlets & JSP)
- HTML / CSS
- Oracle Database (via `ojdbc11.jar`)
- Apache Tomcat or any Java EE compatible server
- ---

## How to Run / Implement Using Apache Tomcat

### Prerequisites
- JDK 8 or higher installed
- Apache Tomcat 9 or 10
- Oracle Database
- `ojdbc11.jar` JDBC driver
1. Download or clone the project.
2. Build the project to generate the WAR file (if not already available).

---

### Step 1: Database Setup
1. Create required tables in Oracle Database (Users, Items, Claims, etc.).
2. Update database credentials in `DBConnection.java`:
   ```java
   String url = "jdbc:oracle:thin:@localhost:1521:xe";
   String username = "your_db_username";
   String password = "your_db_password";
