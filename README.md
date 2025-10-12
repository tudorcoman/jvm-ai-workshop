# jvm-ai-workshop
Migrating Python AI Prototypes to Cross-Platform Solutions

## Project Overview

This project demonstrates how to migrate Python AI prototypes to cross-platform JVM-based solutions using Java 21 and Kotlin. It includes:
- Integration with LangChain4j for AI/LLM capabilities
- Spring Boot REST API framework
- PostgreSQL with pgvector for vector embeddings
- Multik for multidimensional arrays in Kotlin
- ONNX model export for sentence transformers

## Prerequisites

- Java 21 (JDK 21)
- Docker and Docker Compose
- Python 3.8+ (for model export)
- Gradle 8.5+ (included via wrapper)

## Project Structure

```
jvm-ai-workshop/
├── src/
│   └── main/
│       ├── java/com/workshop/ai/          
│       │   └── controller/
│       │       └── HealthController.java          # Java REST controller example
│       ├── kotlin/com/workshop/ai/        
│       │   ├── JvmAiWorkshopApplication.kt        # Spring Boot main application
│       │   ├── controller/
│       │   │   └── WelcomeController.kt           # Kotlin REST controller example
│       │   ├── model/
│       │   │   └── Document.kt                    # JPA entity for PostgreSQL
│       │   ├── repository/
│       │   │   └── DocumentRepository.kt          # Spring Data JPA repository
│       │   └── service/
│       │       ├── EmbeddingService.kt            # LangChain4j embeddings
│       │       └── VectorOperationsService.kt     # Multik array operations
│       └── resources/
│           └── application.yml                    # Spring Boot configuration
├── local-setup/
│   ├── docker-compose.yml                         # PostgreSQL with pgvector
│   └── init.sql                                   # Database initialization
├── export_model_to_onnx.py                        # Python script to export models
├── requirements.txt                               # Python dependencies
├── quickstart.sh                                  # Quick setup script
├── build.gradle                                   # Gradle build configuration (Groovy)
└── gradlew / gradlew.bat                          # Gradle wrapper scripts
```

## Key Components

### Java Components
- **HealthController**: REST API endpoint demonstrating Java 21 features

### Kotlin Components
- **JvmAiWorkshopApplication**: Main Spring Boot application
- **WelcomeController**: REST API endpoint demonstrating Kotlin integration
- **Document**: JPA entity with Hibernate annotations for PostgreSQL
- **DocumentRepository**: Spring Data JPA repository interface
- **EmbeddingService**: LangChain4j integration for text embeddings using MiniLM-L6-v2
- **VectorOperationsService**: Multik library usage for array operations

### Database
- PostgreSQL 16 with pgvector extension for vector similarity search
- Automatic schema generation via Hibernate DDL

### AI/ML Components
- **LangChain4j**: Provides embeddings using the MiniLM-L6-v2 model
- **Multik**: Kotlin library for multidimensional arrays (similar to NumPy)
- **ONNX Export**: Python script to export sentence-transformers models

## Getting Started

### Quick Start (All-in-One)

For a quick setup, run the quickstart script:

```bash
chmod +x quickstart.sh
./quickstart.sh
```

This script will:
1. Check prerequisites (Docker and Java)
2. Start PostgreSQL with pgvector
3. Build the project
4. Display next steps

### Manual Setup

Alternatively, follow these steps manually:

### 1. Start PostgreSQL Database

Start the PostgreSQL database with pgvector extension:

```bash
cd local-setup
docker-compose up -d
```

This will start a PostgreSQL 16 database with:
- Database: `aiworkshop`
- User: `workshop`
- Password: `workshop123`
- Port: `5432`
- pgvector extension enabled

### 2. Export Sentence Transformer Model to ONNX

Create a virtual environment and install Python dependencies:

```bash
# Create virtual environment
python -m venv venv

# Activate virtual environment
# On Linux/Mac:
source venv/bin/activate
# On Windows:
# venv\Scripts\activate

# Install dependencies
pip install -r requirements.txt
```

Run the export script:

```bash
python export_model_to_onnx.py
```

This will download the `sentence-transformers/all-MiniLM-L6-v2` model and export it to ONNX format in the `models/onnx` directory.

### 3. Build the Project

Build the Java/Kotlin application:

```bash
./gradlew build
```

### 4. Run the Application

Start the Spring Boot application:

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

## Dependencies

### Java/Kotlin Dependencies

- **Spring Boot 3.2.0** - Web framework and dependency injection
- **LangChain4j 0.34.0** - AI/LLM integration framework
- **Multik 0.2.3** - Kotlin multidimensional arrays
- **Hibernate 6.4.0** - ORM for database operations
- **PostgreSQL Driver** - Database connectivity
- **pgvector 0.1.4** - Vector similarity search support
- **Kotlin 1.9.20** - Kotlin language support

### Database

- **PostgreSQL 16** with **pgvector** extension for vector embeddings

## Configuration

The application configuration is in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/aiworkshop
    username: workshop
    password: workshop123
  jpa:
    hibernate:
      ddl-auto: update
server:
  port: 8080
```

## Development

### Running Tests

```bash
./gradlew test
```

### REST API Endpoints

The application exposes the following REST endpoints:

**Java Controller:**
- `GET /api/health` - Health check endpoint
  ```json
  {
    "status": "UP",
    "message": "JVM AI Workshop is running",
    "language": "Java 21"
  }
  ```

**Kotlin Controller:**
- `GET /api/kotlin/welcome` - Welcome endpoint
  ```json
  {
    "message": "Welcome to JVM AI Workshop",
    "language": "Kotlin",
    "description": "Migrating Python AI Prototypes to Cross-Platform Solutions"
  }
  ```

### Cleaning Build

```bash
./gradlew clean
```

### Database Management

Stop the database:

```bash
cd local-setup
docker-compose down
```

Stop and remove volumes (deletes all data):

```bash
cd local-setup
docker-compose down -v
```

## Model Export Details

The `export_model_to_onnx.py` script:
1. Downloads the MiniLM-L6-v2 sentence transformer model
2. Converts it to ONNX format for cross-platform inference
3. Saves the model and tokenizer configuration
4. Verifies the exported model works correctly

The exported model can be used in Java/Kotlin applications using ONNX Runtime.

## License

This project is for educational purposes.
