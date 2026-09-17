# 🏫 School Management System

A **RESTful School Management System** built with **Spring Boot** and **MongoDB**.
The system provides APIs for managing students, teachers, parents, subjects, sections, grades, examinations, attendance, academic years, and user authentication.

The project follows a layered backend architecture with **REST APIs, JWT Authentication, Role-Based Authorization, DTOs, Validation, Exception Handling, Redis, Swagger/OpenAPI, and Testing**.

---

## 🚀 Features

* 🔐 User Registration & Login
* 🔑 JWT Authentication
* 🛡️ Role-Based Authorization
* 👨‍🎓 Student Management
* 👨‍🏫 Teacher Management
* 👨‍👩‍👦 Parent Management
* 📚 Subject Management
* 🏫 Grade & Section Management
* 📖 Student Enrollment & Grades
* 📝 Exam Management
* 📅 Exam Schedule Management
* 📊 Exam Result Management
* 🕐 Student Attendance Management
* 📆 Academic Year Management
* 🔗 Subject-Teacher Assignment
* ⚡ Redis Integration
* ✅ Request Validation
* 🚨 Global Exception Handling
* 📄 Swagger/OpenAPI Documentation
* 🧪 Unit & API Testing
* 📑 Pagination & Sorting

---

## 🛠️ Technologies Used

| Technology          | Purpose                        |
| ------------------- | ------------------------------ |
| Java                | Programming Language           |
| Spring Boot         | Backend Framework              |
| Spring Security     | Authentication & Authorization |
| JWT                 | Token-Based Authentication     |
| Spring Data MongoDB | Database Access                |
| MongoDB             | Database                       |
| Redis               | Temporary/Caching Data         |
| Maven               | Dependency Management          |
| Lombok              | Reduce Boilerplate Code        |
| Mockito             | Unit Testing                   |
| JUnit               | Testing Framework              |
| MockMvc             | Controller/API Testing         |
| Swagger / OpenAPI   | API Documentation              |
| Git & GitHub        | Version Control                |

---

## 🏗️ Project Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MongoDB
```

Additional layers/components:

```text
Client
  ↓
Controller
  ↓
DTO / Validation
  ↓
Service
  ↓
Repository
  ↓
MongoDB

Spring Security
      ↓
JWT Authentication
      ↓
Role-Based Authorization
```

---

# 📦 Main Modules

## 👤 User Management

Provides APIs for:

* Creating users
* Finding users
* Updating users
* Deleting users
* Managing user roles

---

## 🔐 Authentication

Authentication APIs provide:

* User Login
* JWT Token Generation
* Registration
* Email Verification
* Password-related authentication functionality

Example token response:

```json
{
  "token": "JWT_TOKEN"
}
```

---

## 👨‍🎓 Student Management

Student module manages:

* Student creation
* Student information
* Student-parent relationships
* Student enrollment
* Student grades
* Student attendance
* Student exam results

---

## 👨‍🏫 Teacher Management

Teacher module manages:

* Teacher creation
* Teacher information
* Teacher assignments
* Subject-teacher relationships

---

## 👨‍👩‍👦 Parent Management

Parent module manages:

* Parent creation
* Parent information
* Parent-student relationships

---

## 📚 Subject Management

Subject APIs provide CRUD operations for school subjects.

---

## 🏫 Grade & Section Management

Grades and sections can be created and managed independently.

Example Section Request:

```json
{
  "name": "SSA",
  "gradeId": "6aac4d1481297ffe726c9eae",
  "romeNo": "CB1_23"
}
```

Example Response:

```text
Section Created Successfully
```

---

## 📖 Enrolled Grades

The enrollment module manages the relationship between:

* Student
* Section
* Academic Year

Example request:

```json
{
  "sectionId": "section-id",
  "studentId": "student-id",
  "academicYearId": "academic-year-id",
  "status": "ACTIVE"
}
```

---

## 👨‍🏫 Subject Teacher Assignment

Teachers can be assigned to subjects and sections for a specific academic year.

### Create Assignment

```http
POST /api/v1/subjectTeacher/create
```

Example request:

```json
{
  "sectionId": "section-id",
  "subjectId": "subject-id",
  "teacherId": "teacher-id",
  "academicYearId": "academic-year-id"
}
```

### Update Assignment

```http
PUT /api/v1/subjectTeacher/update
```

### Find Assignment

```http
GET /api/v1/subjectTeacher/find/{id}
```

### Delete Assignment

```http
DELETE /api/v1/subjectTeacher/delete/{id}
```

---

# 📝 Examination System

The examination system includes:

* Exam creation
* Exam schedules
* Exam results
* Section-based exam results
* Student-specific exam results

Main modules:

```text
Exam
Exam Schedule
Exam Result
```

---

# 🕐 Attendance System

Attendance management supports:

* Individual student attendance
* Section/class attendance
* Attendance requests
* Attendance records

The system provides APIs for both student-level and section-level attendance management.

---

# 📆 Academic Year

Academic Year APIs allow administrators to manage school academic years and associate them with:

* Students
* Sections
* Subjects
* Teachers
* Exams
* Enrollment

---

# 🔐 Security

The application uses **Spring Security + JWT** for securing APIs.

Authentication flow:

```text
User Login
    ↓
Authentication
    ↓
JWT Token Generated
    ↓
Client Sends JWT
    ↓
Spring Security Filter
    ↓
Role Validation
    ↓
Protected API
```

Protected requests u
