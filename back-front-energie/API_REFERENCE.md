# API Reference - Énergie Facile

## Base URL
```
http://localhost:8080/api
```

## Headers
```
Content-Type: application/json
Accept: application/json
```

---

## 📊 Dashboard Endpoints

### Get Current Consumption
**GET** `/dashboard/current-consumption`

Récupère la consommation actuelle (mois courant) pour tous les types d'énergie.

**Response:**
```json
{
  "electricity": {
    "value": 3160,
    "unit": "kWh",
    "trend": 5.2
  },
  "water": {
    "value": 52600,
    "unit": "L",
    "trend": -3.8
  },
  "gas": {
    "value": 1360,
    "unit": "kWh",
    "trend": 12.5
  },
  "total": {
    "value": 4520,
    "unit": "kWh eq.",
    "trend": 2.1
  }
}
```

### Get Monthly Data
**GET** `/dashboard/monthly-data`

Récupère les données de consommation mensuelles pour les 12 derniers mois.

**Response:**
```json
[
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
  },
  {
    "id": 2,
    "year": 2024,
    "month": 2,
    "monthName": "FEBRUARY",
    "electricityValue": 280,
    "waterValue": 4200,
    "gasValue": 165,
    "totalValue": 4645,
    "trend": -7.1
  }
]
```

### Get Weekly Data
**GET** `/dashboard/weekly-data`

Récupère les données de consommation quotidienne de la semaine actuelle.

**Response:**
```json
[
  {
    "id": 1,
    "date": "2024-01-08",
    "electricityValue": 45,
    "waterValue": 650,
    "gasValue": 28,
    "totalValue": 723
  },
  {
    "id": 2,
    "date": "2024-01-09",
    "electricityValue": 52,
    "waterValue": 720,
    "gasValue": 32,
    "totalValue": 804
  }
]
```

### Get Daily Data
**GET** `/dashboard/daily-data`

Récupère tous les enregistrements quotidiens disponibles.

**Response:** Tableau des `DailyConsumption`

### Get Active Alerts
**GET** `/dashboard/alerts`

Récupère toutes les alertes actives.

**Response:**
```json
[
  {
    "id": 1,
    "type": "DANGER",
    "title": "Consommation de gaz élevée",
    "message": "Votre consommation de gaz a augmenté de 12.5%...",
    "isActive": true
  }
]
```

---

## 📝 Consumption Records Endpoints

### Create Record
**POST** `/consumption-records`

Crée un nouvel enregistrement de consommation.

**Request Body:**
```json
{
  "type": "ELECTRICITY",
  "value": 45.5,
  "unit": "kWh",
  "recordedAt": "2024-01-15T14:30:00",
  "notes": "Pic de consommation détecté"
}
```

**Response:**
```json
{
  "id": 100,
  "type": "ELECTRICITY",
  "value": 45.5,
  "unit": "kWh",
  "recordedAt": "2024-01-15T14:30:00",
  "notes": "Pic de consommation détecté",
  "createdAt": "2024-01-15T14:35:00"
}
```

### Get Records by Type
**GET** `/consumption-records/type/{type}`

Récupère tous les enregistrements d'un type spécifique.

**Parameters:**
- `type` (path): `ELECTRICITY`, `WATER`, ou `GAS`

**Response:** Tableau de `ConsumptionRecord`

### Get Records by Date Range
**GET** `/consumption-records/date-range`

Récupère les enregistrements dans une plage de dates.

**Query Parameters:**
- `startDate` (required): `2024-01-01T00:00:00`
- `endDate` (required): `2024-01-31T23:59:59`

**Response:** Tableau de `ConsumptionRecord`

### Get Records by Type and Date Range
**GET** `/consumption-records/type-date-range`

Récupère les enregistrements d'un type spécifique dans une plage de dates.

**Query Parameters:**
- `type` (required): `ELECTRICITY`, `WATER`, ou `GAS`
- `startDate` (required): ISO DateTime
- `endDate` (required): ISO DateTime

**Response:** Tableau de `ConsumptionRecord`

### Get Total Consumption
**GET** `/consumption-records/total`

Récupère le total de consommation pour un type et une plage de dates.

**Query Parameters:**
- `type` (required): `ELECTRICITY`, `WATER`, ou `GAS`
- `startDate` (required): ISO DateTime
- `endDate` (required): ISO DateTime

**Response:**
```json
2847.5
```

---

## 📅 Daily Consumption Endpoints

### Create Daily Consumption
**POST** `/daily-consumption`

Crée un enregistrement de consommation quotidienne.

**Request Body:**
```json
{
  "date": "2024-01-15",
  "electricityValue": 45.2,
  "waterValue": 650.0,
  "gasValue": 28.5,
  "totalValue": 723.7
}
```

**Response:** `DailyConsumption` créé avec `id`

### Get Daily by Date
**GET** `/daily-consumption/{date}`

Récupère la consommation d'une date spécifique.

**Parameters:**
- `date` (path): Format `YYYY-MM-DD` (ex: `2024-01-15`)

**Response:** `DailyConsumption` ou 404 Not Found

### Get Daily by Date Range
**GET** `/daily-consumption/range`

Récupère les consommations quotidiennes sur une plage.

**Query Parameters:**
- `startDate` (required): `YYYY-MM-DD`
- `endDate` (required): `YYYY-MM-DD`

**Response:** Tableau de `DailyConsumption`

### Get All Daily
**GET** `/daily-consumption/all`

Récupère tous les enregistrements quotidiens.

**Response:** Tableau de `DailyConsumption`

---

## 📊 Monthly Consumption Endpoints

### Create Monthly Consumption
**POST** `/monthly-consumption`

Crée un enregistrement de consommation mensuelle.

**Request Body:**
```json
{
  "year": 2024,
  "month": 1,
  "electricityValue": 320,
  "waterValue": 4500,
  "gasValue": 180,
  "totalValue": 5000,
  "trend": null
}
```

**Response:** `MonthlyConsumption` créé

### Get Monthly by Year and Month
**GET** `/monthly-consumption/{year}/{month}`

Récupère la consommation d'un mois spécifique.

**Parameters:**
- `year` (path): Ex: `2024`
- `month` (path): Ex: `1` (janvier) à `12` (décembre)

**Response:** `MonthlyConsumption` ou 404 Not Found

### Get Monthly by Year
**GET** `/monthly-consumption/year/{year}`

Récupère la consommation de tous les mois d'une année.

**Parameters:**
- `year` (path): Ex: `2024`

**Response:** Tableau de `MonthlyConsumption`

### Get All Monthly
**GET** `/monthly-consumption/all`

Récupère tous les enregistrements mensuels, triés par mois décroissant.

**Response:** Tableau de `MonthlyConsumption`

---

## 🚨 Alerts Endpoints

### Create Alert
**POST** `/alerts`

Crée une nouvelle alerte.

**Request Body:**
```json
{
  "type": "WARNING",
  "title": "Consommation élevée",
  "message": "Votre consommation d'électricité a augmenté.",
  "isActive": true
}
```

**Response:** `Alert` créée avec `id`

### Get Active Alerts
**GET** `/alerts/active`

Récupère toutes les alertes actives.

**Response:** Tableau d'`Alert` avec `isActive = true`

### Get Alerts by Type
**GET** `/alerts/type/{type}`

Récupère toutes les alertes d'un type spécifique.

**Parameters:**
- `type` (path): `DANGER`, `WARNING`, ou `SUCCESS`

**Response:** Tableau d'`Alert`

### Update Alert
**PUT** `/alerts/{id}`

Met à jour une alerte existante.

**Parameters:**
- `id` (path): ID de l'alerte

**Request Body:**
```json
{
  "type": "WARNING",
  "title": "Titre mis à jour",
  "message": "Message mis à jour",
  "isActive": false
}
```

**Response:** `Alert` mise à jour ou 404 Not Found

### Delete Alert
**DELETE** `/alerts/{id}`

Supprime une alerte.

**Parameters:**
- `id` (path): ID de l'alerte

**Response:** 204 No Content ou 404 Not Found

---

## 📋 Data Models

### EnergyType Enum
```
ELECTRICITY  - Électricité (unité: kWh)
WATER        - Eau (unité: L)
GAS          - Gaz (unité: kWh)
```

### AlertType Enum
```
DANGER   - Alerte critique (rouge)
WARNING  - Avertissement (orange)
SUCCESS  - Succès (vert)
```

### ConsumptionRecord
```json
{
  "id": 1,
  "type": "ELECTRICITY",
  "value": 45.5,
  "unit": "kWh",
  "recordedAt": "2024-01-15T14:30:00",
  "notes": "Commentaires optionnels",
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
  "title": "Consommation de gaz élevée",
  "message": "Votre consommation de gaz...",
  "isActive": true
}
```

### CurrentConsumption
```json
{
  "electricity": { "value": 3160, "unit": "kWh", "trend": 5.2 },
  "water": { "value": 52600, "unit": "L", "trend": -3.8 },
  "gas": { "value": 1360, "unit": "kWh", "trend": 12.5 },
  "total": { "value": 4520, "unit": "kWh eq.", "trend": 2.1 }
}
```

---

## 🔧 Codes d'Erreur HTTP

| Code | Signification |
|------|--------------|
| 200 | OK - Succès |
| 201 | Created - Ressource créée |
| 204 | No Content - Suppression réussie |
| 400 | Bad Request - Paramètres invalides |
| 404 | Not Found - Ressource non trouvée |
| 500 | Server Error - Erreur serveur |

---

## 📝 Exemples cURL

### Créer un enregistrement
```bash
curl -X POST http://localhost:8080/api/consumption-records \
  -H "Content-Type: application/json" \
  -d '{
    "type": "ELECTRICITY",
    "value": 45.5,
    "unit": "kWh",
    "recordedAt": "2024-01-15T14:30:00",
    "notes": "Pic détecté"
  }'
```

### Récupérer la consommation actuelle
```bash
curl http://localhost:8080/api/dashboard/current-consumption
```

### Récupérer les alertes actives
```bash
curl http://localhost:8080/api/alerts/active
```

### Créer une alerte
```bash
curl -X POST http://localhost:8080/api/alerts \
  -H "Content-Type: application/json" \
  -d '{
    "type": "WARNING",
    "title": "Consommation élevée",
    "message": "Attention : consommation anormale",
    "isActive": true
  }'
```

### Supprimer une alerte
```bash
curl -X DELETE http://localhost:8080/api/alerts/1
```

---

## ⚙️ Filtres et Paramètres Supportés

### Formats de Date
- **LocalDate** : `YYYY-MM-DD` (ex: `2024-01-15`)
- **LocalDateTime** : `YYYY-MM-DDTHH:MM:SS` (ex: `2024-01-15T14:30:00`)

### Types d'Énergie
- `ELECTRICITY` - Électricité
- `WATER` - Eau
- `GAS` - Gaz naturel

### Types d'Alerte
- `DANGER` - Critique
- `WARNING` - Avertissement
- `SUCCESS` - Succès

---

## 🔐 CORS Configuration

Les requêtes sont autorisées depuis :
- `http://localhost:5173` (Vite dev)
- `http://localhost:3000` (Production)

Méthodes autorisées :
- GET, POST, PUT, DELETE, OPTIONS

Headers autorisés :
- Tous (`*`)

---

Dernière mise à jour : January 2024
