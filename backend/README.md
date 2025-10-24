# Students Backend (Spring Boot 3, Java 17)

## Run
./mvnw spring-boot:run

## Test
./mvnw test

## Endpoints
GET  /students
POST /students   { "name": "Alice", "grade": "A" }
GET  /students/{id}

Windows (cmd.exe) commands:

# Run unit & integration tests
mvn -f backend\pom.xml test

# Run a single test class
mvn -f backend\pom.xml -Dtest=StudentServiceTest test

# Build package (skip tests)
mvn -f backend\pom.xml package -DskipTests

Notes:
- Maven's surefire plugin produces XML reports in backend/target/surefire-reports/ which CI uploads as artifacts.

<!-- Database/Cloud DB instructions removed per repository owner request. The backend now uses an in-memory implementation and does not require Postgres or Supabase. -->

