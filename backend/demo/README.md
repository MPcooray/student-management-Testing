# Demo: Running the backend tests (JUnit)

This short README shows exactly how to run the demo during your 3-minute live demo slot. The project already uses JUnit 5 + Spring Boot Test + MockMvc. The demo will show running a focused test and explaining what to point out.

Prerequisites
- Java 17+ installed and on PATH
- Maven 3.6+ (or use the included `mvnw` wrapper)
- Repository checked out locally

Paths
- Backend working directory: `backend`

Quick demo flow (90–120 seconds)
1. Open a terminal in the project root.
2. Run a single focused test (controller test) to show a meaningful example of assertions, fixtures and MockMvc.
   - Windows (cmd.exe):
     ```cmd
     cd backend
     mvn -DtrimStackTrace=false -Dtest=StudentControllerTest test
     ```
   - Bash (macOS / Linux / Git Bash on Windows):
     ```bash
     cd backend
     mvn -DtrimStackTrace=false -Dtest=StudentControllerTest test
     ```
   Expected output: Maven will run the single test class and print a JUnit summary like `Tests run: 3, Failures: 0, Errors: 0`.

3. Point out while the test runs:
   - Setup/teardown: show `@BeforeEach` / `@AfterEach` in `StudentControllerTest` where the in-memory service is reset.
   - Assertions: show `andExpect(...)` assertions in the test (response status, JSON fields).
   - Integration-style testing: explain how `@SpringBootTest` + `@AutoConfigureMockMvc` boots Spring and tests controllers with MockMvc.

4. (Optional) Run the full test suite to show all tests and CI parity:
   ```bash
   cd backend
   mvn test
   ```

5. (Optional) Show the test reports produced by Surefire (useful if you mention CI):
   - Reports live in: `backend/target/surefire-reports/`
   - Example to print report summary on Windows:
     ```cmd
     type backend\target\surefire-reports\TEST-com.example.students.StudentControllerTest.xml
     ```

What to say (30–60 seconds bullets)
- "We're using JUnit 5 with Spring Boot Test and MockMvc to test our REST controllers."
- "Tests are small, fast and run in CI via GitHub Actions; the workflow uploads surefire reports for visibility."  
- "Key test features shown: fixtures (@BeforeEach), assertions (andExpect/assertEquals), and end-to-end controller flow without a browser." 

Fallback plan
- If the demo environment is unreliable, record a 60–90 second terminal screencast that runs the `mvn -Dtest=StudentControllerTest test` command and narrate the key points.

Files to open during demo
- `backend/src/test/java/com/example/students/StudentControllerTest.java` — focused assertions & setup
- `backend/src/main/java/com/example/students/services/StudentService.java` — in-memory service (explain no external DB required)

Notes for markers / graders
- Emphasize how MockMvc simulates HTTP requests and assertions on JSON responses.
- Mention test isolation (service cleared between tests) and that the tests run quickly (<10s locally).

That's it — if you want, I can also add a short PowerPoint slide with these demo steps or a pre-recorded fallback screencast file.
