# 🗺️ API Routes Map

## Base URL
```
http://localhost:8080/api
```

## Routes Complètes

```
API Root: http://localhost:8080/api/

├── 📊 DASHBOARD
│   ├── GET  /dashboard/current-consumption
│   ├── GET  /dashboard/monthly-data
│   ├── GET  /dashboard/weekly-data
│   ├── GET  /dashboard/daily-data
│   └── GET  /dashboard/alerts
│
├── 📝 CONSUMPTION RECORDS
│   ├── POST    /consumption-records
│   ├── GET     /consumption-records/type/{type}
│   ├── GET     /consumption-records/date-range?startDate=...&endDate=...
│   ├── GET     /consumption-records/type-date-range?type=...&startDate=...&endDate=...
│   └── GET     /consumption-records/total?type=...&startDate=...&endDate=...
│
├── 📅 DAILY CONSUMPTION
│   ├── POST    /daily-consumption
│   ├── GET     /daily-consumption/{date}
│   ├── GET     /daily-consumption/range?startDate=...&endDate=...
│   └── GET     /daily-consumption/all
│
├── 📆 MONTHLY CONSUMPTION
│   ├── POST    /monthly-consumption
│   ├── GET     /monthly-consumption/{year}/{month}
│   ├── GET     /monthly-consumption/year/{year}
│   └── GET     /monthly-consumption/all
│
└── 🚨 ALERTS
    ├── POST    /alerts
    ├── GET     /alerts/active
    ├── GET     /alerts/type/{type}
    ├── PUT     /alerts/{id}
    └── DELETE  /alerts/{id}
```

## Controllers (5)

### 🎯 DashboardController
- Routes de vue d'ensemble
- Données combinées
- 5 endpoints

### 📊 ConsumptionRecordController
- Gestion enregistrements
- Filtrage avancé
- 6 endpoints

### 📅 DailyConsumptionController
- Données quotidiennes
- Plages de dates
- 4 endpoints

### 📆 MonthlyConsumptionController
- Données mensuelles
- Par année/mois
- 4 endpoints

### 🚨 AlertController
- CRUD alertes
- Filtrage par type
- 5 endpoints

## Méthodes HTTP

```
POST   - Créer nouvelle ressource
GET    - Récupérer données
PUT    - Mettre à jour ressource
DELETE - Supprimer ressource
```

## Paramètres Typiques

```
Path Parameters:
  {type}      → ELECTRICITY | WATER | GAS
  {date}      → YYYY-MM-DD (ex: 2024-01-15)
  {year}      → YYYY (ex: 2024)
  {month}     → M ou MM (ex: 1 ou 01)
  {id}        → Numérique (ex: 1)

Query Parameters:
  startDate   → ISO DateTime (YYYY-MM-DDTHH:MM:SS)
  endDate     → ISO DateTime (YYYY-MM-DDTHH:MM:SS)
  type        → ELECTRICITY | WATER | GAS
```

## Response Status Codes

```
200 OK              - Requête réussie
201 Created         - Ressource créée
204 No Content      - Suppression réussie
400 Bad Request     - Paramètres invalides
404 Not Found       - Ressource non trouvée
500 Server Error    - Erreur serveur
```

## Quick Reference Table

| Endpoint | Méthode | Description |
|----------|---------|-------------|
| `/dashboard/current-consumption` | GET | Consommation actuelle (mois) |
| `/dashboard/monthly-data` | GET | Tous les mois |
| `/dashboard/weekly-data` | GET | Semaine actuelle |
| `/dashboard/daily-data` | GET | Tous les jours |
| `/dashboard/alerts` | GET | Alertes actives |
| `/consumption-records` | POST | Créer enregistrement |
| `/consumption-records/type/{type}` | GET | Records par type |
| `/consumption-records/date-range` | GET | Records par dates |
| `/consumption-records/total` | GET | Total consommation |
| `/daily-consumption` | POST | Créer quotidienne |
| `/daily-consumption/{date}` | GET | Jour spécifique |
| `/daily-consumption/range` | GET | Plage jours |
| `/daily-consumption/all` | GET | Tous les jours |
| `/monthly-consumption` | POST | Créer mensuelle |
| `/monthly-consumption/{year}/{month}` | GET | Mois spécifique |
| `/monthly-consumption/year/{year}` | GET | Année complète |
| `/monthly-consumption/all` | GET | Tous les mois |
| `/alerts` | POST | Créer alerte |
| `/alerts/active` | GET | Alertes actives |
| `/alerts/type/{type}` | GET | Alertes par type |
| `/alerts/{id}` | PUT | Mettre à jour |
| `/alerts/{id}` | DELETE | Supprimer |

## Response Models

### CurrentConsumption
```json
{
  "electricity": { "value": 3160, "unit": "kWh", "trend": 5.2 },
  "water": { "value": 52600, "unit": "L", "trend": -3.8 },
  "gas": { "value": 1360, "unit": "kWh", "trend": 12.5 },
  "total": { "value": 4520, "unit": "kWh eq.", "trend": 2.1 }
}
```

### ConsumptionRecord
```json
{
  "id": 1,
  "type": "ELECTRICITY",
  "value": 45.5,
  "unit": "kWh",
  "recordedAt": "2024-01-15T14:30:00",
  "notes": "Optional",
  "createdAt": "2024-01-15T14:35:00"
}
```

### DailyConsumption
```json
{
  "id": 1,
  "date": "2024-01-15",
  "electricityValue": 45.2,
  "waterValue": 650.0,
  "gasValue": 28.5,
  "totalValue": 723.7
}
```

### MonthlyConsumption
```json
{
  "id": 1,
  "year": 2024,
  "month": 1,
  "monthName": "JANUARY",
  "electricityValue": 320,
  "waterValue": 4500,
  "gasValue": 180,
  "totalValue": 5000,
  "trend": null
}
```

### Alert
```json
{
  "id": 1,
  "type": "DANGER",
  "title": "High gas consumption",
  "message": "Your gas consumption...",
  "isActive": true
}
```

## Enum Values

### EnergyType
- `ELECTRICITY` (kWh)
- `WATER` (L)
- `GAS` (kWh)

### AlertType
- `DANGER` (Red)
- `WARNING` (Orange)
- `SUCCESS` (Green)

## CORS Headers

```
Access-Control-Allow-Origin: http://localhost:5173, http://localhost:3000
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
Access-Control-Allow-Headers: *
Access-Control-Allow-Credentials: true
Access-Control-Max-Age: 3600
```

## Test Examples

### Get Current Consumption
```bash
curl http://localhost:8080/api/dashboard/current-consumption
```

### Create Record
```bash
curl -X POST http://localhost:8080/api/consumption-records \
  -H "Content-Type: application/json" \
  -d '{
    "type":"ELECTRICITY",
    "value":50.5,
    "unit":"kWh",
    "recordedAt":"2024-01-15T14:30:00"
  }'
```

### Get Monthly Data
```bash
curl http://localhost:8080/api/dashboard/monthly-data
```

### Get Alerts
```bash
curl http://localhost:8080/api/alerts/active
```

### Create Alert
```bash
curl -X POST http://localhost:8080/api/alerts \
  -H "Content-Type: application/json" \
  -d '{
    "type":"WARNING",
    "title":"Test",
    "message":"Test message",
    "isActive":true
  }'
```

---

**Total Endpoints: 24** 🎯  
**Controllers: 5** 🎨  
**HTTP Methods: 4** 📝  
**Response Models: 5** 📦  

Pour la documentation détaillée, voir **API_REFERENCE.md**
