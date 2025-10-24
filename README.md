# CI badges

[![Backend CI - Tests](https://github.com/MPcooray/student-management-Testing/actions/workflows/backend-tests.yml/badge.svg?branch=development)](https://github.com/MPcooray/student-management-Testing/actions/workflows/backend-tests.yml)
[![Full CI](https://github.com/MPcooray/student-management-Testing/actions/workflows/full-stack-ci.yml/badge.svg?branch=development)](https://github.com/MPcooray/student-management-Testing/actions/workflows/full-stack-ci.yml)

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

## ✅ Code coverage & CI

We collect code coverage for the backend using JaCoCo. The project includes a JaCoCo Maven plugin that generates HTML and XML reports when you run the tests.

Local commands (inside the repository):

Windows (cmd):
```cmd
cd backend
mvn test jacoco:report
start "" "target\site\jacoco\index.html"
```

macOS / Linux (bash):
```bash
cd backend
mvn test jacoco:report
open target/site/jacoco/index.html
```

Where to find the reports:
- HTML report (readable): `backend/target/site/jacoco/index.html`
- XML report (for CI upload / badge providers): `backend/target/site/jacoco/jacoco.xml`
- JaCoCo exec file: `backend/target/jacoco.exec`

CI integration (recommended)
- Option A: Upload the `jacoco.xml` to a coverage provider (Codecov, SonarCloud).
	Example (Codecov action snippet) to add in your test workflow after running tests:

```yaml
- name: Upload coverage to Codecov
	uses: codecov/codecov-action@v4
	with:
		files: backend/target/site/jacoco/jacoco.xml
		fail_ci_if_error: true
```

- Option B: Publish the HTML report as a workflow artifact (so reviewers can download and view):

```yaml
- name: Upload Jacoco HTML report
	uses: actions/upload-artifact@v4
	with:
		name: jacoco-report
		path: backend/target/site/jacoco
```

Adding a coverage badge to this README
- If you use Codecov, after the repository is connected on codecov.io you can add the following badge to the top of this README (replace `MPcooray/student-management-Testing` with your repo path):

```markdown
[![Coverage Status](https://codecov.io/gh/MPcooray/student-management-Testing/branch/Testing/graph/badge.svg?token=YOUR_CODECOV_TOKEN)](https://codecov.io/gh/MPcooray/student-management-Testing)
```

Notes and suggestions
- JaCoCo generates `jacoco.xml` which is widely accepted by coverage services. Use that for CI uploads.
- For the assignment/demo we already have ~98% coverage locally — you can either keep the small `StudentsApplication` main uncovered or add a tiny test to exercise or exclude it from JaCoCo if you want 100%.


## 👨‍💻 Author

**Manula Cooray**
**Madara Chamudini**
- SLIIT Y3S1 Advanced Software Engineering Assignment

## 📄 License

This project is created for educational purposes as part of SLIIT coursework.
