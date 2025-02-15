# Employee Data Exporter

## Overview
The **Employee Data Exporter** is a Java-based application that retrieves employee data from a MySQL database and exports it into an Excel file (.xlsx). The application ensures **thread safety** to handle multiple concurrent export requests.

## Features
- **Thread-safe Excel file generation** using Apache POI
- **Efficient database connection pooling** with HikariCP
- **Concurrency management** using ExecutorService
- **Locking mechanisms** to prevent file corruption
- **Auto-generated unique filenames** for each export

## Technologies Used
- Java (JDK 11+)
- Apache POI (Excel file handling)
- HikariCP (Database connection pooling)
- MySQL (Database)
- ExecutorService (Thread management)

## Prerequisites
- Java 11 or later
- MySQL database with an "employees" table
- Maven (for dependency management)

## Setup & Installation
### 1. Clone the Repository
```sh
git clone https://github.com/sm-junior0/ExcelGen.git
cd ExcelGen
```

### 2. Configure Database
Ensure you have a MySQL database running with the following table:
```sql
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    department VARCHAR(255),
    salary DECIMAL(10,2)
);
```
Update `DatabaseConnection.java` with your MySQL credentials:
```java
config.setJdbcUrl("jdbc:mysql://localhost:3306/your_database");
config.setUsername("your_username");
config.setPassword("your_password");
```

### 3. Build & Run
Use Maven to build and run the application:
```sh
mvn clean package
java -jar target/employee-exporter.jar
```

## Usage
- The application automatically processes multiple concurrent export requests.
- Each request generates a unique Excel file with employee data.
- The exported files are saved in the project directory.

## Concurrency Handling
- Uses **ExecutorService** to manage concurrent export requests.
- **ReentrantLock** prevents race conditions while writing Excel files.
- **HikariCP** ensures safe database access across multiple threads.

## Future Enhancements
- Implement a user interface (Web or CLI) for managing exports.
- Add support for exporting to CSV and PDF formats.
- Introduce logging and monitoring tools.

## License
This project is licensed under the MIT License.

## Author
Developed by **Manene Junior**

---
For issues or feature requests, please open an issue on GitHub.

