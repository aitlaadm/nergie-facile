#!/bin/bash
echo "========================================"
echo "Energie Facile - Backend Startup"
echo "========================================"
echo ""

# Check if Docker is running
if ! docker ps > /dev/null 2>&1; then
    echo "ERROR: Docker is not running. Please start Docker first."
    exit 1
fi

echo "[1/3] Starting MySQL Docker container..."
docker-compose up -d

# Wait for MySQL to be ready
echo "[2/3] Waiting for MySQL to be ready..."
sleep 5

echo "[3/3] Starting Spring Boot application..."
echo ""
echo "The application will start on http://localhost:8080"
echo "API endpoints available at http://localhost:8080/api"
echo ""

mvn clean install && mvn spring-boot:run
