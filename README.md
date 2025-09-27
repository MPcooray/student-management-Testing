# 🎓 Student Management System

A modern, full-stack web application for managing student records with a beautiful UI and comprehensive CRUD operations.

## 🚀 Features

- **Create** new student records with grades (A-F)
- **Read** and display all students in a beautiful table
- **Update** student grades directly in the table
- **Delete** students with confirmation dialog
- **Filter** students by grade
- **Responsive** design that works on all devices
- **Modern UI** with gradients, animations, and professional styling

## 🛠️ Tech Stack

### Backend
- **Java 17** with Spring Boot 3.3.4
- **Spring Web** for REST API
- **Spring Validation** for input validation
- **Maven** for dependency management
- **JUnit 5** for testing

### Frontend
- **React 18** with Vite
- **Modern CSS** with gradients and animations
- **Responsive** design
- **ES6+** JavaScript

## 📦 Project Structure

```
student-app/
├── backend/           # Spring Boot API
│   ├── src/main/java/
│   ├── src/test/java/
│   └── pom.xml
├── frontend/          # React Application
│   ├── src/
│   ├── package.json
│   └── vite.config.js
└── README.md
```

## 🚦 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Node.js 16+ and npm

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
The API will be available at `http://localhost:8080`

### Frontend Setup
```bash
cd frontend
npm install
npm run dev
```
The frontend will be available at `http://localhost:5173`

## 📡 API Endpoints

- `GET /students` - Get all students (optional `?grade=A` filter)
- `POST /students` - Create a new student
- `GET /students/{id}` - Get student by ID
- `PUT /students/{id}` - Update student grade
- `DELETE /students/{id}` - Delete a student

## 🎨 UI Features

- **Modern Design**: Glass-morphism cards with gradient backgrounds
- **Color-coded Grades**: Each grade has its own color (A=Green, B=Blue, etc.)
- **Interactive Elements**: Hover effects, smooth transitions
- **Mobile Responsive**: Works perfectly on phones and tablets
- **User-friendly**: Clear navigation and intuitive interface

## 🧪 Testing

Run backend tests:
```bash
cd backend
mvn test
```

## 👨‍💻 Author

**Manula Cooray**
- SLIIT Y3S1 Advanced Software Engineering Assignment

## 📄 License

This project is created for educational purposes as part of SLIIT coursework.