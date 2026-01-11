# Guide d'Intégration Frontend-Backend

## Configuration du Frontend

### 1. Installer les dépendances

```bash
cd front-energie-facile/nergie-facile
npm install
# ou
bun install
```

### 2. Configurer l'API

Créez un fichier de configuration API :

```typescript
// src/lib/api.ts
const API_BASE_URL = 'http://localhost:8080/api';

export const API = {
  // Dashboard
  getCurrentConsumption: async () => {
    const response = await fetch(`${API_BASE_URL}/dashboard/current-consumption`);
    return response.json();
  },
  getMonthlyData: async () => {
    const response = await fetch(`${API_BASE_URL}/dashboard/monthly-data`);
    return response.json();
  },
  getWeeklyData: async () => {
    const response = await fetch(`${API_BASE_URL}/dashboard/weekly-data`);
    return response.json();
  },
  getDailyData: async () => {
    const response = await fetch(`${API_BASE_URL}/dashboard/daily-data`);
    return response.json();
  },
  getAlerts: async () => {
    const response = await fetch(`${API_BASE_URL}/dashboard/alerts`);
    return response.json();
  },
  
  // Consumption Records
  createRecord: async (data) => {
    const response = await fetch(`${API_BASE_URL}/consumption-records`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return response.json();
  },
  
  // Daily Consumption
  createDaily: async (data) => {
    const response = await fetch(`${API_BASE_URL}/daily-consumption`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return response.json();
  },
  
  // Monthly Consumption
  createMonthly: async (data) => {
    const response = await fetch(`${API_BASE_URL}/monthly-consumption`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return response.json();
  },
  
  // Alerts
  createAlert: async (data) => {
    const response = await fetch(`${API_BASE_URL}/alerts`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return response.json();
  },
  updateAlert: async (id, data) => {
    const response = await fetch(`${API_BASE_URL}/alerts/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return response.json();
  },
  deleteAlert: async (id) => {
    await fetch(`${API_BASE_URL}/alerts/${id}`, { method: 'DELETE' });
  },
};
```

### 3. Utiliser React Query

```typescript
// src/hooks/useConsumptionData.ts
import { useQuery } from '@tanstack/react-query';
import { API } from '@/lib/api';

export const useCurrentConsumption = () => {
  return useQuery({
    queryKey: ['currentConsumption'],
    queryFn: API.getCurrentConsumption,
    refetchInterval: 60000, // Refresh every minute
  });
};

export const useMonthlyData = () => {
  return useQuery({
    queryKey: ['monthlyData'],
    queryFn: API.getMonthlyData,
  });
};

export const useAlerts = () => {
  return useQuery({
    queryKey: ['alerts'],
    queryFn: API.getAlerts,
    refetchInterval: 30000,
  });
};
```

### 4. Mettre à jour les composants

```typescript
// src/pages/Index.tsx
import { useCurrentConsumption, useMonthlyData, useAlerts } from '@/hooks/useConsumptionData';

const Index = () => {
  const { data: consumption, isLoading } = useCurrentConsumption();
  const { data: monthlyData } = useMonthlyData();
  const { data: alerts } = useAlerts();

  if (isLoading) return <div>Chargement...</div>;

  return (
    <div className="space-y-6">
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <EnergyCard
          title="Consommation totale"
          value={consumption?.total?.value}
          unit={consumption?.total?.unit}
          icon={Activity}
          trend={consumption?.total?.trend}
          variant="total"
        />
        {/* ... autres cartes */}
      </div>
      
      <ConsumptionChart data={monthlyData} />
      
      <div className="grid gap-4">
        {alerts?.map((alert) => (
          <ConsumptionAlert key={alert.id} alert={alert} />
        ))}
      </div>
    </div>
  );
};
```

### 5. Formulaire de saisie

```typescript
// src/pages/Saisie.tsx
import { useMutation } from '@tanstack/react-query';
import { API } from '@/lib/api';

const Saisie = () => {
  const createMutation = useMutation({
    mutationFn: API.createRecord,
    onSuccess: () => {
      // Rafraîchir les données
      queryClient.invalidateQueries({ queryKey: ['currentConsumption'] });
    },
  });

  const handleSubmit = (formData) => {
    createMutation.mutate({
      type: formData.type, // ELECTRICITY, WATER, GAS
      value: formData.value,
      unit: formData.unit,
      recordedAt: new Date().toISOString(),
      notes: formData.notes,
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      {/* Champs du formulaire */}
    </form>
  );
};
```

## Environnements

Créez un fichier `.env` dans le frontend :

```env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_API_TIMEOUT=30000
```

Utilisez-le dans votre config :

```typescript
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;
```

## Démarrage Complet

### Terminal 1 - Backend

```bash
cd back-front-energie/backend
./run.sh  # Linux/Mac
# ou
run.bat   # Windows
```

### Terminal 2 - Frontend

```bash
cd front-energie-facile/nergie-facile
npm run dev
```

Accédez à l'application sur `http://localhost:5173`

## Types TypeScript pour le Frontend

```typescript
// src/types/energy.ts
export interface EnergyValue {
  value: number;
  unit: string;
  trend: number;
}

export interface CurrentConsumption {
  electricity: EnergyValue;
  water: EnergyValue;
  gas: EnergyValue;
  total: EnergyValue;
}

export interface MonthlyConsumption {
  id: number;
  year: number;
  month: number;
  monthName: string;
  electricityValue: number;
  waterValue: number;
  gasValue: number;
  totalValue: number;
  trend: number;
}

export interface DailyConsumption {
  id: number;
  date: string;
  electricityValue: number;
  waterValue: number;
  gasValue: number;
  totalValue: number;
}

export interface Alert {
  id: number;
  type: 'DANGER' | 'WARNING' | 'SUCCESS';
  title: string;
  message: string;
  isActive: boolean;
}
```

## Troubleshooting

### Erreur CORS

Si vous recevez une erreur CORS, assurez-vous que :
1. Le backend est en cours d'exécution sur `http://localhost:8080`
2. Le frontend est sur `http://localhost:5173`
3. Les origines sont correctement configurées dans `CorsConfig.java`

### Pas de données

1. Vérifiez que MySQL est en cours d'exécution :
   ```bash
   docker ps
   ```

2. Vérifiez la connexion à la base de données :
   ```bash
   docker logs energie_facile_db
   ```

3. Vérifiez les logs du backend pour les erreurs

### Port déjà utilisé

Si le port 8080 est occupé, modifiez dans `application.properties` :
```properties
server.port=8081
```

## Documentation API

Consultez [backend/README.md](backend/README.md) pour la documentation complète de l'API.

## Ressources Utiles

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [React Query](https://tanstack.com/query/latest)
- [Vite Documentation](https://vitejs.dev)

