@echo off
REM Test script for API endpoints
REM Make sure the backend is running on http://localhost:8080

setlocal enabledelayedexpansion

set "API_URL=http://localhost:8080/api"

echo.
echo ========================================
echo  API Test Script - Energie Facile
echo ========================================
echo.
echo API URL: %API_URL%
echo.

REM Test 1: Get current consumption
echo [TEST 1] Getting current consumption...
curl %API_URL%/dashboard/current-consumption
echo.
echo.

REM Test 2: Get monthly data
echo [TEST 2] Getting monthly data...
curl %API_URL%/dashboard/monthly-data
echo.
echo.

REM Test 3: Get alerts
echo [TEST 3] Getting active alerts...
curl %API_URL%/dashboard/alerts
echo.
echo.

REM Test 4: Create new consumption record
echo [TEST 4] Creating new consumption record...
curl -X POST %API_URL%/consumption-records ^
  -H "Content-Type: application/json" ^
  -d "{\"type\":\"ELECTRICITY\",\"value\":50.5,\"unit\":\"kWh\",\"recordedAt\":\"2024-01-15T14:30:00\",\"notes\":\"Test record\"}"
echo.
echo.

REM Test 5: Create new alert
echo [TEST 5] Creating new alert...
curl -X POST %API_URL%/alerts ^
  -H "Content-Type: application/json" ^
  -d "{\"type\":\"WARNING\",\"title\":\"Test Alert\",\"message\":\"This is a test alert\",\"isActive\":true}"
echo.
echo.

echo ========================================
echo  All tests completed!
echo ========================================
echo.

pause
