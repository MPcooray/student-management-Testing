@echo off
REM Demo script to run a focused test in the backend (Windows cmd)
cd /d "%~dp0\..\"
echo Running StudentControllerTest (focused demo)...
mvn -DtrimStackTrace=false -Dtest=StudentControllerTest test
if %ERRORLEVEL% neq 0 (
  echo
  echo TESTS FAILED
  exit /b %ERRORLEVEL%
)
echo
echo Focused test finished. To run all tests: mvn test
pause
