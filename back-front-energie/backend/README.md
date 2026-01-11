# Énergie Facile - Backend

Backend Spring Boot pour l'application de suivi de consommation d'électricité, eau et gaz.

## Architecture MVC

Le projet suit une architecture MVC avec les répertoires suivants :

```
backend/
├── src/main/java/com/energiefacile/
│   ├── controllers/       # Contrôleurs REST (API Endpoints)
│   ├── services/          # Logique métier
│   ├── repositories/      # Accès aux données (JPA)
│   ├── models/            # Entités JPA
│   ├── dto/              # Data Transfer Objects
│   ├── config/           # Configuration de l'application
│   └── EnergieFacileApplication.java  # Point d'entrée
├── src/main/resources/
│   └── application.properties  # Configuration
├── docker-compose.yml    # Configuration Docker MySQL
└── pom.xml              # Dépendances Maven
```

## Prérequis

- Java 17+
- Maven 3.6+
- Docker et Docker Compose

## Installation

### 1. Démarrer la base de données MySQL

```bash
cd backend
docker-compose up -d
```

Cela crée un conteneur MySQL avec :
- Base de données : `energie_facile`
- Utilisateur : `energie_user`
- Mot de passe : `energie_password`
- Port : `3306`

### 2. Construire et lancer l'application

```bash
mvn clean install
mvn spring-boot:run
```

L'application démarre sur `http://localhost:8080`

## API Endpoints

### Dashboard
- `GET /api/dashboard/current-consumption` - Consommation actuelle
- `GET /api/dashboard/monthly-data` - Données mensuelles
- `GET /api/dashboard/weekly-data` - Données hebdomadaires
- `GET /api/dashboard/daily-data` - Données quotidiennes
- `GET /api/dashboard/alerts` - Alertes actives

### Consommation Records
- `POST /api/consumption-records` - Créer un enregistrement
- `GET /api/consumption-records/type/{type}` - Enregistrements par type
- `GET /api/consumption-records/date-range` - Enregistrements par plage de dates
- `GET /api/consumption-records/total` - Total de consommation

### Consommation Quotidienne
- `POST /api/daily-consumption` - Créer une consommation quotidienne
- `GET /api/daily-consumption/{date}` - Consommation du jour
- `GET /api/daily-consumption/range` - Plage de dates
- `GET /api/daily-consumption/all` - Tous les enregistrements

### Consommation Mensuelle
- `POST /api/monthly-consumption` - Créer une consommation mensuelle
- `GET /api/monthly-consumption/{year}/{month}` - Consommation du mois
- `GET /api/monthly-consumption/year/{year}` - Consommation de l'année
- `GET /api/monthly-consumption/all` - Tous les enregistrements

### Alertes
- `POST /api/alerts` - Créer une alerte
- `GET /api/alerts/active` - Alertes actives
- `GET /api/alerts/type/{type}` - Alertes par type
- `PUT /api/alerts/{id}` - Mettre à jour une alerte
- `DELETE /api/alerts/{id}` - Supprimer une alerte

## Configuration

Les paramètres de configuration se trouvent dans `src/main/resources/application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/energie_facile
spring.datasource.username=energie_user
spring.datasource.password=energie_password
spring.jpa.hibernate.ddl-auto=update
```

## Base de données

Les données sont initialisées automatiquement au démarrage via `DataInitializer.java`. Les tables créées sont :

- `consumption_records` - Enregistrements de consommation
- `daily_consumption` - Consommation quotidienne
- `monthly_consumption` - Consommation mensuelle
- `alerts` - Alertes

## CORS

Le CORS est configuré pour autoriser les requêtes du frontend :
- `http://localhost:5173` (Vite dev server)
- `http://localhost:3000` (Production)

## Énumérations

### EnergyType
- `ELECTRICITY` - Électricité (kWh)
- `WATER` - Eau (L)
- `GAS` - Gaz (kWh)

### AlertType
- `DANGER` - Alerte critique
- `WARNING` - Avertissement
- `SUCCESS` - Succès/Félicitations

## Arrêter Docker

```bash
docker-compose down
```

Pour supprimer les données :
```bash
docker-compose down -v
```

## Troubleshooting

### Connexion à MySQL refusée
Vérifiez que Docker est en cours d'exécution et que le conteneur est démarré :
```bash
docker ps
```

### Port 3306 déjà utilisé
Si le port est occupé, modifiez dans `docker-compose.yml` :
```yaml
ports:
  - "3307:3306"  # Changer le port externe
```

## License

MIT
