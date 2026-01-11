# 🚀 Démarrage Rapide - Énergie Facile

## ✅ Prérequis

- [Java 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [Docker Desktop](https://www.docker.com/products/docker-desktop)
- [Node.js 16+](https://nodejs.org/) (pour le frontend)

## 📋 Structure du Projet

```
back-front-energie/
├── backend/                    # Application Spring Boot
│   ├── src/
│   │   ├── main/java/         # Code Java
│   │   └── main/resources/    # Configuration
│   ├── docker-compose.yml     # MySQL
│   ├── pom.xml               # Dépendances Maven
│   └── README.md             # Documentation backend
│
├── INTEGRATION_GUIDE.md        # Guide intégration frontend/backend
└── STRUCTURE.txt              # Détails architecture
```

## 🚀 Étape 1 : Démarrer le Backend

### Option A : Utiliser le script automatisé (Recommandé)

**Windows :**
```cmd
cd back-front-energie\backend
run.bat
```

**Linux/Mac :**
```bash
cd back-front-energie/backend
chmod +x run.sh
./run.sh
```

### Option B : Démarrage manuel

**Terminal 1 - Démarrer MySQL**
```bash
cd back-front-energie/backend
docker-compose up -d
```

**Terminal 2 - Démarrer l'application**
```bash
cd back-front-energie/backend
mvn clean install
mvn spring-boot:run
```

✅ L'application démarre sur : **http://localhost:8080**

## 🎨 Étape 2 : Démarrer le Frontend

```bash
cd front-energie-facile/nergie-facile
npm install  # ou: bun install
npm run dev  # ou: bun dev
```

✅ L'application est accessible sur : **http://localhost:5173**

## 🧪 Tester l'API

Une fois les deux serveurs lancés, testez les endpoints :

```bash
# Consommation actuelle
curl http://localhost:8080/api/dashboard/current-consumption

# Données mensuelles
curl http://localhost:8080/api/dashboard/monthly-data

# Alertes actives
curl http://localhost:8080/api/dashboard/alerts
```

## 📊 Accédez à l'Application

- **Frontend**: [http://localhost:5173](http://localhost:5173)
- **Backend API**: [http://localhost:8080/api](http://localhost:8080/api)
- **MySQL**: localhost:3306

## 🛑 Arrêter les Services

**Arrêter le Backend** : Appuyez sur `Ctrl+C` dans le terminal

**Arrêter MySQL** :
```bash
cd back-front-energie/backend
docker-compose down
```

Pour supprimer toutes les données :
```bash
docker-compose down -v
```

## 📝 Données d'Initialisation

Le backend remplit automatiquement la base de données avec :
- ✅ 12 mois de données de consommation
- ✅ 7 jours de données quotidiennes
- ✅ 21 enregistrements hebdomadaires
- ✅ 3 alertes d'exemple

## 🔐 Identifiants MySQL

```
Host: localhost
Port: 3306
Database: energie_facile
User: energie_user
Password: energie_password
```

## 📚 Documentation Complète

- [Backend README](backend/README.md) - API endpoints et configuration
- [Integration Guide](INTEGRATION_GUIDE.md) - Intégration frontend/backend
- [Architecture](STRUCTURE.txt) - Détails architecture MVC

## ⚠️ Troubleshooting

### Docker ne démarre pas
```bash
# Vérifiez que Docker Desktop est lancé
docker ps

# Vérifiez l'état du conteneur
docker-compose ps
```

### Port 8080 déjà utilisé
Modifiez `application.properties` :
```properties
server.port=8081
```

### MySQL ne se connecte pas
```bash
# Redémarrer Docker
docker-compose down
docker-compose up -d
```

### Port 3306 déjà utilisé
Modifiez `docker-compose.yml` :
```yaml
ports:
  - "3307:3306"  # Nouveau port externe
```

### Vérifier les logs
```bash
# Logs backend
docker logs energie_facile_db

# Logs Maven
mvn spring-boot:run -X
```

## 🎯 Points Clés de l'Architecture

### MVC
- **Models** : Entités JPA (Consommation, Alertes)
- **Views** : API REST JSON
- **Controllers** : Endpoints Spring Boot

### Services
- Logique métier centralisée
- Transformation Entity → DTO
- Gestion des transactions

### Base de Données
- MySQL 8.0 en Docker
- Données pré-remplies au démarrage
- Migrations automatiques via Hibernate

## 🔗 Flux de Données

```
Frontend (React)
     ↓
API REST (Spring Boot)
     ↓
Services (Logique métier)
     ↓
Repository JPA
     ↓
MySQL Database
```

## 📱 Fonctionnalités Disponibles

✅ Suivi consommation électricité/eau/gaz  
✅ Graphiques mensuels et hebdomadaires  
✅ Alertes intelligentes  
✅ Historique complet  
✅ Dashboard en temps réel  
✅ Saisie de données  

## 🎓 Apprentissage

Consultez le code pour apprendre :
- **Spring Boot** : `backend/src/main/java/com/energiefacile/`
- **React/TypeScript** : `front-energie-facile/nergie-facile/src/`
- **JPA/Hibernate** : `backend/src/main/java/com/energiefacile/models/`
- **REST API** : `backend/src/main/java/com/energiefacile/controllers/`

## 📞 Support

Pour l'aide :
1. Consultez les READMEs (backend/ et root)
2. Vérifiez les logs Docker/Maven
3. Testez avec curl ou Postman
4. Vérifiez les ports (8080, 3306, 5173)

---

**Prêt ? Commencez par :** `run.bat` ou `./run.sh` dans `backend/` 🚀
