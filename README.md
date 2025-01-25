# Market-Shop Project

## Description
The Market-Shop project is a **normal trade application** built using Java and Spring Boot. This application simulates the functionality of a market, allowing users to manage products, customers, and sales efficiently. It is designed to demonstrate best practices in back-end development and object-oriented programming principles.

## Features
- **Product Management**: Add, update, and delete products.
- **Customer Management**: Maintain customer data.
- **Sales Management**: Handle transactions and generate reports.
- **Database Integration**: Fully integrated with a relational database using Spring Data JPA.
- **API Endpoints**: RESTful APIs for seamless integration.
- **Robust Design**: Scalable and maintainable architecture.

## Technology Stack
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL
- **Build Tool**: Maven
- **Testing**: JUnit

## Project Structure
```
Market-Shop
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.marketshop
│   │   └── resources
│   └── test
├── pom.xml
└── README.md
```

## Installation and Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Huseynov-Huseyn/Market-Shop.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd Market-Shop/Codes/market-shop
   ```

3. **Configure the database:**
   - Update the `application.properties` file in `src/main/resources` with your database details:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/marketshop
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     spring.jpa.hibernate.ddl-auto=update
     ```

4. **Build the project:**
   ```bash
   mvn clean install
   ```

5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

6. **Access the application:**
   - The application runs on `http://localhost:8080`.

## Usage
- Use API endpoints for CRUD operations on products, customers, and sales.
- Test the application using Postman or a similar tool.

## Contributions
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m 'Add new feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Open a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Author
**Huseyn Huseynov**
- Email: huseynhuseyn343@gmail.com
- GitHub: [Huseynov-Huseyn](https://github.com/Huseynov-Huseyn)
- LinkedIn: [Huseyn Huseynov](https://www.linkedin.com/in/huseynhuseynov-/)

---

### Acknowledgments
Special thanks to everyone who contributed to the development of this project.
