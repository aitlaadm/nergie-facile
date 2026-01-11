# 🚀 Guide de Démarrage Complet - Frontend + Backend

## 📋 Prérequis

### Pour le Backend
- **Java 17+** [Télécharger](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** [Télécharger](https://maven.apache.org/download.cgi)
- **Docker Desktop** [Télécharger](https://www.docker.com/products/docker-desktop)

### Pour le Frontend
- **Node.js 16+** ou **Bun** [Télécharger Node](https://nodejs.org/) | [Bun](https://bun.sh)

### Vérifier l'installation

```powershell
# Backend
java -version
mvn -version
docker --version

# Frontend
node -v
npm -v
# Ou si vous utilisez Bun
bun -v
```

---

## 🎯 Architecture Client-Serveur

```
┌─────────────────────────────────────────────────────────┐
│                  Frontend (React + TypeScript)          │
│  http://localhost:5173                                  │
├─────────────────────────────────────────────────────────┤
│ • Pages React/TypeScript                                │
│ • React Query pour la gestion d'état                    │
│ • shadcn/ui pour les composants                         │
│ • Axios/Fetch pour les appels API                       │
└──────────────────────────┬──────────────────────────────┘
                           │
                    REST API (JSON)
                           │
┌──────────────────────────▼──────────────────────────────┐
│              Backend (Spring Boot + MySQL)              │
│  http://localhost:8080/api                              │
├─────────────────────────────────────────────────────────┤
│ • 24 endpoints REST                                     │
│ • Architecture MVC                                      │
│ • JPA/Hibernate ORM                                     │
│ • MySQL 8.0 (Docker)                                   │
└─────────────────────────────────────────────────────────┘
```

---

## 📦 Installation et Dépendances

### Backend - Maven

Les dépendances sont dans `backend/pom.xml` :

```xml
<!-- Déjà installées -->
- Spring Boot 3.2.1
- Spring Data JPA
- MySQL Connector
- Lombok
- Validation
```

Aucune installation supplémentaire requise pour le backend.

### Frontend - Node.js / npm / Bun

Dépendances essentielles (déjà dans `package.json`) :

```json
{
  "dependencies": {
    "react": "^18",
    "react-dom": "^18",
    "react-router-dom": "^6",
    "@tanstack/react-query": "^5.83.0",
    "lucide-react": "^0.462.0",
    "date-fns": "^3.6.0"
  },
  "devDependencies": {
    "typescript": "^5",
    "vite": "^latest"
  }
}
```

---

## 🚀 Étape 1 : Démarrer le Backend

### Option A : Démarrage Automatique (Windows)

```powershell
cd c:\Users\Simo\Desktop\energie-facile\back-front-energie\backend
.\run.bat
```

**Attendez que :**
- Docker lance MySQL
- Spring Boot démarre
- Message final: `Started EnergieFacileApplication`

### Option B : Démarrage Automatique (Mac/Linux)

```bash
cd ~/back-front-energie/backend
chmod +x run.sh
./run.sh
```

### Option C : Démarrage Manuel

**Terminal 1 - Lancer MySQL :**
```powershell
cd backend
docker-compose up -d

# Vérifier que MySQL est prêt
docker ps
```

**Terminal 2 - Lancer Spring Boot :**
```powershell
cd backend
mvn clean install
mvn spring-boot:run
```

### ✅ Vérification Backend

Une fois démarré, testez les endpoints :

```powershell
# Consommation actuelle
curl http://localhost:8080/api/dashboard/current-consumption

# Données mensuelles
curl http://localhost:8080/api/dashboard/monthly-data

# Alertes
curl http://localhost:8080/api/dashboard/alerts
```

**Ou utiliser le script de test :**
```powershell
cd backend
.\test-api.bat
```

---

## 🎨 Étape 2 : Démarrer le Frontend

### Option A : Avec npm

```powershell
cd c:\Users\Simo\Desktop\energie-facile\front-energie-facile\nergie-facile

# Installer les dépendances (première fois seulement)
npm install

# Démarrer le serveur de développement
npm run dev
```

### Option B : Avec Bun (Plus rapide)

```powershell
cd front-energie-facile\nergie-facile

# Installer les dépendances
bun install

# Démarrer
bun run dev
```

### ✅ Vérification Frontend

L'application sera accessible sur :
```
http://localhost:5173
```

**La page doit afficher :**
- Les 4 cartes d'énergie avec données du backend
- Deux graphiques (mensuel et hebdomadaire)
- La liste des alertes
- Pas d'erreurs CORS

---

## 🧪 Tester l'Intégration Complète

### 1. Vérifier la communication

Ouvrez DevTools du navigateur (F12) et allez dans l'onglet **Network** :

1. Rechargez la page (F5)
2. Vous devriez voir plusieurs requêtes vers `localhost:8080/api`
3. Status : `200 OK`

### 2. Vérifier les données

Console JavaScript (F12 → Console) :

```javascript
// Tester l'API directement
fetch('http://localhost:8080/api/dashboard/current-consumption')
  .then(r => r.json())
  .then(d => console.log(d))
```

### 3. Créer un enregistrement

Allez à la page **Saisie** et soumettez un formulaire. Le backend devrait enregistrer les données.

### 4. Vérifier dans la Base de Données

```bash
# Accéder à MySQL
docker exec -it energie_facile_db mysql -uenergie_user -penergie_password energie_facile

# Vérifier les données
SELECT COUNT(*) FROM consumption_records;
SELECT COUNT(*) FROM monthly_consumption;
SELECT * FROM alerts LIMIT 3;
```

---

## 📝 Configuration Personnalisée

### Changer le port du Backend

Si le port 8080 est déjà utilisé :

**Fichier :** `backend/src/main/resources/application.properties`

```properties
server.port=8081
```

Puis dans le frontend `.env` :
```
VITE_API_BASE_URL=http://localhost:8081/api
```

### Changer le port du Frontend

Si le port 5173 est déjà utilisé :

**Package.json :**
```json
"scripts": {
  "dev": "vite --port 5174"
}
```

---

## ⚙️ Dépannage Courant

### ❌ Erreur : « Cannot connect to MySQL »

**Solution :**
```powershell
# Vérifier que Docker est lancé
docker ps

# Redémarrer les conteneurs
docker-compose down
docker-compose up -d

# Attendre 10 secondes que MySQL soit prêt
```

### ❌ Erreur : « CORS error »

**Vérifier :**
1. Backend sur `http://localhost:8080`
2. Frontend sur `http://localhost:5173`
3. CORS configuré dans `CorsConfig.java`

### ❌ Erreur : « 404 Not Found »

**Vérifier :**
1. Backend démarre correctement
2. URL de l'API correcte dans `.env`
3. Endpoints correctement nommés

### ❌ Erreur npm : « node_modules pas trouvé »

**Solution :**
```powershell
# Réinstaller les dépendances
npm install
# ou
bun install

# Supprimer le cache
npm cache clean --force
```

### ❌ Port 8080 déjà utilisé

```powershell
# Trouver quel processus utilise le port
netstat -ano | findstr :8080

# Arrêter le processus
taskkill /PID <PID> /F
```

---

## 🔄 Workflow de Développement

### Terminal 1 - Backend
```powershell
cd backend
mvn spring-boot:run
# Logs de l'application
```

### Terminal 2 - Frontend
```powershell
cd front-energie-facile\nergie-facile
npm run dev
# Auto-reload à chaque changement
```

### Terminal 3 - Optionnel (Vérification)
```powershell
# Tests API
cd backend
.\test-api.bat

# Ou requêtes curl
curl http://localhost:8080/api/dashboard/alerts
```

### Navigateur
```
http://localhost:5173
```

---

## 📊 Fichiers de Configuration

### Frontend
- `.env` - Variables d'environnement (API URL, timeout)
- `vite.config.ts` - Configuration Vite
- `package.json` - Dépendances et scripts

### Backend
- `application.properties` - Configuration Spring Boot
- `docker-compose.yml` - Configuration MySQL
- `pom.xml` - Dépendances Maven

---

## 🔗 Structure des Fichiers Créés

### Frontend

```
src/
├── lib/
│   ├── api.ts              ← Service API (tous les endpoints)
│   ├── types.ts            ← Types TypeScript pour les données
│   └── utils.ts            ← Utilitaires
├── hooks/
│   ├── useApi.ts           ← Hooks React Query (nouvelles données)
│   └── use-toast.ts        ← Existant
├── pages/
│   ├── Index.tsx           ← Page modifiée (utilise l'API)
│   ├── Historique.tsx      ← À mettre à jour
│   ├── Saisie.tsx          ← À mettre à jour
│   └── NotFound.tsx
└── ...
```

### Configuration
```
.env                ← Configuration locale
.env.example        ← Template pour la documentation
```

---

## ✨ Fonctionnalités Disponibles

### ✅ Implémentées
- Affichage du dashboard avec données réelles
- Graphiques alimentés par l'API
- Alertes dynamiques
- Communication via HTTP REST

### 🔄 À Implémenter (Optionnel)
- Page Saisie : formulaire pour créer des enregistrements
- Page Historique : filtrer par type et date
- Pagination pour les longs listes
- Authentification utilisateur
- Mode sombre/clair

---

## 📚 Documentation Supplémentaire

### Backend
- `backend/README.md` - Guide complet backend
- `backend/API_REFERENCE.md` - Tous les endpoints avec exemples

### Frontend
- Voir `INTEGRATION_GUIDE.md` pour plus de détails

### Générale
- `QUICKSTART.md` - Démarrage ultra-rapide
- `API_ROUTES_MAP.md` - Vue d'ensemble des routes

---

## 🎉 Résumé des Commandes Essentielles

### Démarrer tout

**Windows :**
```powershell
# Terminal 1
cd backend && .\run.bat

# Terminal 2 (après que le backend démarre)
cd front-energie-facile\nergie-facile && npm run dev
```

**Mac/Linux :**
```bash
# Terminal 1
cd backend && chmod +x run.sh && ./run.sh

# Terminal 2
cd front-energie-facile/nergie-facile && npm run dev
```

### Accéder à l'Application
```
Frontend: http://localhost:5173
Backend: http://localhost:8080/api
```

### Arrêter
```powershell
# Frontend : Ctrl+C dans le terminal npm
# Backend : Ctrl+C dans le terminal Maven

# Arrêter Docker
cd backend && docker-compose down
```

---

## ✅ Checklist de Vérification

- [ ] Java 17+ installé
- [ ] Maven installé
- [ ] Docker Desktop lancé
- [ ] Node.js/npm ou Bun installé
- [ ] Backend démarre sans erreurs
- [ ] MySQL accessible
- [ ] Frontend démarre sans erreurs
- [ ] Accès à http://localhost:5173
- [ ] Données visibles sur le dashboard
- [ ] Pas d'erreurs CORS
- [ ] API endpoints testés

---

## 🚀 Prochaines Étapes

1. **Compléter les pages Saisie et Historique** avec l'API
2. **Ajouter des tests** (Jest + React Testing Library)
3. **Déployer** sur un serveur (Heroku, AWS, Netlify)
4. **Ajouter l'authentification** (JWT)
5. **Optimiser les performances** (caching, pagination)

---

**Vous êtes maintenant prêt à développer! 🎉**

Pour toute question, consultez les documentations spécifiques.
