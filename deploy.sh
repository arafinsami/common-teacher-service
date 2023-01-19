
#!/bin/sh

echo "Stopping docker container"
[ -f Dockerfile ] && [ -f  docker-compose.yml ] && docker-compose down

echo "Removing Dockerfile & docker-compose.yml"
[ -f Dockerfile ] && rm Dockerfile
[ -f docker-compose.yml ] && rm docker-compose.yml

echo "Copying Dockerfile.pre to Dockerfile"
cp Dockerfile.pre Dockerfile

echo "Copying docker-compose.yml.pre to docker-compose.yml"
cp docker-compose.yml.pre docker-compose.yml

echo "Running docker container"
docker-compose build
docker-compose up -d

echo "Building service"
docker exec -it eteacher_service gradle clean build

echo "Stopping docker container"
[ -f Dockerfile ] && [ -f  docker-compose.yml ] && docker-compose down

echo "Removing Dockerfile & docker-compose.yml"
[ -f Dockerfile ] && rm Dockerfile
[ -f docker-compose.yml ] && rm docker-compose.yml

echo "Copying Dockerfile.post to Dockerfile"
cp Dockerfile.post Dockerfile

echo "Copying docker-compose.yml.post to docker-compose.yml"
cp docker-compose.yml.post docker-compose.yml

echo "Running docker container"
docker-compose build
docker-compose up -d

echo "Service deployed successfully!"
