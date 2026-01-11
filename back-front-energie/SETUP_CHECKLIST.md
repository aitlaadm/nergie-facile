# ✅ Backend Setup Checklist

## 📋 Vérification de l'Installation

### 1. Configuration Java
- [ ] Java 17+ installé : `java -version`
- [ ] Maven 3.6+ installé : `mvn -version`
- [ ] JAVA_HOME configuré correctement
- [ ] Variables d'environnement configurées

### 2. Docker
- [ ] Docker Desktop installé et lancé
- [ ] Docker fonctionnel : `docker ps`
- [ ] Docker Compose disponible : `docker-compose --version`
- [ ] Port 3306 disponible

### 3. Ports
- [ ] Port 8080 disponible pour Spring Boot
- [ ] Port 5173 disponible pour Vite (frontend)
- [ ] Port 3306 disponible pour MySQL
- [ ] Vérifier : `netstat -ano | findstr :8080` (Windows)
- [ ] Vérifier : `lsof -i :8080` (Mac/Linux)

### 4. Dépôt Git
- [ ] `back-front-energie/` créé
- [ ] Structure des répertoires correcte
- [ ] `.gitignore` présent
- [ ] Fichiers sensibles ignorés

---

## 🏗️ Structure Vérification

### Répertoires Créés
- [ ] `backend/src/main/java/com/energiefacile/models/`
- [ ] `backend/src/main/java/com/energiefacile/repositories/`
- [ ] `backend/src/main/java/com/energiefacile/services/`
- [ ] `backend/src/main/java/com/energiefacile/controllers/`
- [ ] `backend/src/main/java/com/energiefacile/dto/`
- [ ] `backend/src/main/java/com/energiefacile/config/`
- [ ] `backend/src/main/resources/`

### Fichiers de Configuration
- [ ] `pom.xml` présent
- [ ] `docker-compose.yml` présent
- [ ] `application.properties` présent
- [ ] `.gitignore` présent

### Fichiers Java Créés
- [ ] `EnergieFacileApplication.java`
- [ ] Entités (ConsumptionRecord, DailyConsumption, etc.)
- [ ] Repositories (4 fichiers)
- [ ] Services (4 fichiers)
- [ ] Controllers (5 fichiers)
- [ ] DTOs (5 fichiers)
- [ ] Config (CorsConfig, DataInitializer)

### Documentation
- [ ] `README.md` (backend)
- [ ] `QUICKSTART.md` (root)
- [ ] `INTEGRATION_GUIDE.md` (root)
- [ ] `API_REFERENCE.md` (root)
- [ ] `STRUCTURE.txt` (root)

### Scripts
- [ ] `run.bat` (Windows)
- [ ] `run.sh` (Unix/Mac)
- [ ] `test-api.bat` (Windows)
- [ ] `test-api.sh` (Unix/Mac)

---

## 🚀 Démarrage

### Avant de Lancer
- [ ] Tous les prérequis installés
- [ ] Tous les ports disponibles
- [ ] Docker Desktop lancé
- [ ] Terminal overt dans le bon répertoire

### Lancer le Backend
**Option 1 : Script automatisé**
```bash
# Windows
run.bat

# Mac/Linux
chmod +x run.sh
./run.sh
```

**Option 2 : Manuel**
```bash
docker-compose up -d
mvn clean install
mvn spring-boot:run
```

### Vérifications Après Démarrage
- [ ] MySQL container démarré : `docker ps`
- [ ] Database créée : `energie_facile`
- [ ] User créé : `energie_user`
- [ ] Spring Boot démarré sans erreurs
- [ ] Logs visibles dans le terminal

---

## ✅ Tests de Fonctionnement

### Tests Basiques
- [ ] API accessible sur `http://localhost:8080/api`
- [ ] Dashboard endpoint répond : `/dashboard/current-consumption`
- [ ] Monthly data disponible : `/dashboard/monthly-data`
- [ ] Alerts disponibles : `/dashboard/alerts`

### Tests Avancés
Utiliser `test-api.bat` ou `test-api.sh`:
```bash
./test-api.bat     # Windows
./test-api.sh      # Mac/Linux
```

Ou avec curl :
```bash
# Test 1 : Consommation actuelle
curl http://localhost:8080/api/dashboard/current-consumption

# Test 2 : Créer un enregistrement
curl -X POST http://localhost:8080/api/consumption-records \
  -H "Content-Type: application/json" \
  -d '{"type":"ELECTRICITY","value":45.5,"unit":"kWh"}'

# Test 3 : Alertes actives
curl http://localhost:8080/api/alerts/active
```

### Vérifications de Données
- [ ] 12 mois de données mensuelles
- [ ] 7 jours de données quotidiennes
- [ ] 3 alertes d'exemple
- [ ] Données accessible via API

---

## 🔗 Intégration Frontend

### Avant de Connecter le Frontend
- [ ] Backend fonctionnelle et en cours d'exécution
- [ ] API endpoints testés manuellement
- [ ] CORS configuré correctement
- [ ] Données présentes en BD

### Configuration Frontend
- [ ] API base URL configurée : `http://localhost:8080/api`
- [ ] React Query installé
- [ ] Services API créés
- [ ] Hooks personnalisés créés

### Frontend Startup
```bash
cd front-energie-facile/nergie-facile
npm install  # ou bun install
npm run dev  # ou bun dev
```

### Vérifications Intégrées
- [ ] Données du backend affichées
- [ ] Graphiques remplis
- [ ] Alertes visibles
- [ ] Pas d'erreurs CORS

---

## 🐛 Troubleshooting

### Si MySQL ne démarre pas
- [ ] Vérifier Docker : `docker ps`
- [ ] Redémarrer : `docker-compose restart`
- [ ] Logs : `docker logs energie_facile_db`
- [ ] Supprimer : `docker-compose down -v`

### Si Spring Boot ne démarre pas
- [ ] Logs Maven : `mvn spring-boot:run -X`
- [ ] Vérifier port : `netstat -ano | findstr :8080`
- [ ] Lancer manuellement : `mvn clean install`

### Si API ne répond pas
- [ ] Vérifier URL : `http://localhost:8080/api`
- [ ] Vérifier CORS headers
- [ ] Logs backend : regarder le terminal

### Si pas de données
- [ ] Vérifier `DataInitializer.java` s'exécute
- [ ] Vérifier logs d'initialisation
- [ ] Vérifier BD : 
  ```bash
  docker exec -it energie_facile_db mysql -uenergie_user -penergie_password energie_facile
  SELECT COUNT(*) FROM monthly_consumption;
  ```

---

## 📝 Notes Importantes

### Architecture MVC
- **Models** : Entités JPA
- **Views** : Réponses JSON (REST)
- **Controllers** : Endpoints

### Données d'Initialisation
Remplissage automatique via `DataInitializer.java` :
- Données mensuelles 2024
- Données quotidiennes
- Alertes d'exemple
- Enregistrements de consommation

### Configuration par Défaut
- Port API : 8080
- Port MySQL : 3306
- Base de données : energie_facile
- Utilisateur : energie_user
- Mot de passe : energie_password

### Fichiers de Configuration Clés
- `application.properties` : Configuration Spring
- `docker-compose.yml` : Configuration MySQL
- `pom.xml` : Dépendances Maven
- `CorsConfig.java` : CORS

---

## ✨ Points de Contrôle Finaux

### Avant de Considérer Comme Complet
- [ ] Backend complet et documenté
- [ ] API testée et fonctionnelle
- [ ] Base de données remplie
- [ ] CORS configuré
- [ ] Scripts de démarrage fonctionnels
- [ ] Documentation complète
- [ ] Frontend peut se connecter

### Documentation Complète
- [ ] README.md
- [ ] QUICKSTART.md
- [ ] INTEGRATION_GUIDE.md
- [ ] API_REFERENCE.md
- [ ] STRUCTURE.txt
- [ ] Cette checklist

---

**Status Final** ✅ Backend Prêt !

Date de Vérification: _______________
Vérifié par: _______________
Notes: _______________

---

Pour toute question, consultez la documentation dans le répertoire `back-front-energie/`.
