# Course Library Management System

Spring Boot 2.5.4 + Spring Security + Spring Data JPA/Hibernate + MySQL + Thymeleaf (HTML/CSS/JS).

## Setup
1. Install Java 11+ and MySQL 8, and make sure MySQL is running.
2. Edit `src/main/resources/application.properties` and set your MySQL username/password
   (or set the `DB_USERNAME` / `DB_PASSWORD` environment variables).
   The `courselibrary` database and all tables (users, books, authors, categories, publishers, ...)
   are created automatically by Hibernate on first start.
3. Run: `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`) or run `CourselibraryApplication` from your IDE.
4. Open http://localhost:8087

## Features
- Sign up at `/register` (passwords stored as BCrypt hashes in the `users` table)
- Login at `/login`, logout from the navbar
- Home page is public; Books, Authors, Categories, Publishers require login
- All data is stored in MySQL via Hibernate / Spring Data JPA
