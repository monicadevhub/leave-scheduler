🚀 Leave Scheduler System (Java + JDBC + MySQL)


A console-based Leave Management System that enables employees to apply for leave and admins to manage requests.
Built using Java, JDBC, and MySQL.



✅ Features

👨‍💼 Employee

Register & Login

Apply for Leave

View Leave Status

View Leave History



🧑‍💻 Admin

View All Leave Requests

Approve / Reject Leave

Manage Employees

Generate Leave Reports




🛠 Technologies Used

Java

JDBC

MySQL

Eclipse IDE




📁 Project Structure

LeaveScheduler/

 ├── src/
 
 │   └── leavesystem/
 
 │       ├── cli/        -> Menus & UI
 
 │       ├── dao/        -> Database operations
 
 │       ├── model/      -> Entities (User, Leave)
 
 │       ├── service/    -> Business logic
 
 │       └── util/       -> DBConnection
 
 ├── README.md
 
 └── SQL Script (Database Setup)
 


🗄️ Database Setup


CREATE DATABASE IF NOT EXISTS leave_scheduler;


USE leave_scheduler;


DROP TABLE IF EXISTS leaves;


DROP TABLE IF EXISTS users;



CREATE TABLE users (

    id INT PRIMARY KEY AUTO_INCREMENT,
    
    username VARCHAR(50) UNIQUE NOT NULL,
    
    password VARCHAR(255) NOT NULL,
    
    role VARCHAR(20) DEFAULT 'employee',
    
    ct_balance INT DEFAULT 12
    
);


CREATE TABLE leaves (

    id INT PRIMARY KEY AUTO_INCREMENT,
    
    user_id INT,
    
    start_date DATE,
    
    end_date DATE,
    
    days INT,
    
    reason VARCHAR(200),
    
    status VARCHAR(20) DEFAULT 'Pending',
    
    leave_type VARCHAR(10),
    
    FOREIGN KEY (user_id) REFERENCES users(id)
    
);

INSERT INTO users (username, password, role, ct_balance)

VALUES ('admin', 'admin123', 'admin', 12);

INSERT INTO users (username, password, role, ct_balance) VALUES

('moni',  'moni@123',  'employee', 12),

('john',  'john@123',  'employee', 12),

('riya',  'riya@123',  'employee', 12),

('arun',  'arun@123',  'employee', 12),

('kavya', 'kavya@123', 'employee', 12);


INSERT INTO leaves (user_id, start_date, end_date, days, reason, status, leave_type) VALUES

(2, '2025-11-14', '2025-11-20', 7, 'Family Function', 'Pending', 'CT'),

(3, '2025-12-01', '2025-12-02', 2, 'Medical Checkup', 'Approved', 'SL'),

(4, '2025-11-25', '2025-11-27', 3, 'Personal Work', 'Pending', 'PL'),

(5, '2025-11-10', '2025-11-10', 1, 'Fever', 'Rejected', 'SL'),

(6, '2025-11-15', '2025-11-18', 4, 'Travel', 'Approved', 'CT');




▶️ How to Run


Open the project in Eclipse

Add MySQL Connector JAR to the project build path

Update database credentials in DBConnection.java

Run MainApp.java




👩‍💻 Developed & Maintained By

Monica R

BCA • Python Developer • Java Programmer • JDBC • MySQL

