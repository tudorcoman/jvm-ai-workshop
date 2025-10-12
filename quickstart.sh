#!/bin/bash

# Quick Start Script for JVM AI Workshop
# This script sets up and starts the entire development environment

set -e

echo "=============================================="
echo "JVM AI Workshop - Quick Start"
echo "=============================================="
echo ""

# Check prerequisites
echo "Checking prerequisites..."

if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    exit 1
fi

if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 21 or later."
    exit 1
else
    java_version=$(java -version 2>&1 | head -n 1 | awk -F '"' '{print $2}' | cut -d'.' -f1)
    echo "✓ Java version: $(java -version 2>&1 | head -n 1)"
fi

echo ""

# Start PostgreSQL with pgvector
echo "Starting PostgreSQL with pgvector..."
cd local-setup
docker compose up -d

echo "Waiting for PostgreSQL to be ready..."
sleep 5

# Check if PostgreSQL is ready
max_attempts=30
attempt=0
while ! docker compose exec -T postgres pg_isready -U workshop -d aiworkshop > /dev/null 2>&1; do
    attempt=$((attempt + 1))
    if [ $attempt -eq $max_attempts ]; then
        echo "❌ PostgreSQL failed to start after ${max_attempts} attempts"
        exit 1
    fi
    echo "Waiting for PostgreSQL... (attempt $attempt/$max_attempts)"
    sleep 2
done

echo "✓ PostgreSQL is ready"
cd ..

echo ""

# Build the project
echo "Building the project..."
./gradlew build --no-daemon

echo ""
echo "=============================================="
echo "✓ Setup Complete!"
echo "=============================================="
echo ""
echo "Next steps:"
echo "1. Run the application: ./gradlew bootRun"
echo "2. Test endpoints:"
echo "   - Health: curl http://localhost:8080/api/health"
echo "   - Welcome: curl http://localhost:8080/api/kotlin/welcome"
echo ""
echo "3. (Optional) Export ML model to ONNX:"
echo "   - Install Python deps: pip install -r requirements.txt"
echo "   - Run export script: python export_model_to_onnx.py"
echo ""
echo "To stop PostgreSQL: cd local-setup && docker compose down"
echo "=============================================="
