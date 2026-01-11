@echo off
echo ========================================
echo Energie Facile - Backend Startup
echo ========================================
echo.

REM Check if Docker is running
docker ps >nul 2>&1
if errorlevel 1 (
    echo ERROR: Docker is not running. Please start Docker first.
    pause
    exit /b 1
)

echo [1/3] Starting MySQL Docker container...
docker-compose up -d

REM Wait for MySQL to be ready
echo [2/3] Waiting for MySQL to be ready...
timeout /t 5 /nobreak

echo [3/3] Starting Spring Boot application...
echo.
echo The application will start on http://localhost:8080
echo API endpoints available at http://localhost:8080/api
echo.

mvn clean install && mvn spring-boot:run

pause
