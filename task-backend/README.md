# Pasos para crear la imagen de docker y subirla al repositorio

Ejecutamos

```
docker login
```
Generamos el build
```
./mvnw clean package -DskipTests
```
Después generamos la imagen

```
docker build -f src/main/docker/Dockerfile.jvm -t velfin13/task-backend:1.0.0 .
```
Finalmente subimos la imagen al repositorio

```
docker push velfin13/task-backend:1.0.0 
```