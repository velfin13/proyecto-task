# Pasos para levantar el entorno de Staging

Moverse al directorio

```
cd docker-compose/staging
```
Luego levantamos los contenedores

```
docker compose up -d
```
Finalmente nos dirigimos al navegador y abrimos el siguiente url

```
http://localhost:5173/tasks
```

> 💡 **Nota:** Para bajar los contadores, usa el siguiente comando
> 
> ```bash
> docker compose down
> ```


## 🛠️ Tecnologías utilizadas

- Java 17
- Quarkus
- React (TypeScript)
- MySQL 5.7
