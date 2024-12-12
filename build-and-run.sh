#!/bin/bash

# Build and start the containers
docker-compose up --build -d

# Wait for the application to start
echo "Waiting for the application to start..."
sleep 30

# Show the logs
docker-compose logs -f 