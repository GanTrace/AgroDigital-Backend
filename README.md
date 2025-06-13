# AgroDigital Backend

This is the backend for the AgroDigital application, built with Spring Boot and following Domain-Driven Design (DDD) principles.

## Recent Changes

### User Structure Simplification

- **Removed `firstName` and `lastName`**: These fields have been replaced with a single `fullName` field using the `FullName` value object.
- **Deleted `PersonName.java`**: The old `PersonName` value object has been removed from the users context.
- **Removed Role System**: Eliminated the `UserRole` system and all role-related functionality to simplify the user structure.
- **Removed Learning Context**: Completely eliminated the learning context including Students, Courses, and Enrollments to focus on core functionality.

### Domain Layer Changes

- **User Aggregate**: Simplified to use only `FullName`, `EmailAddress`, and `Password`
- **CreateUserCommand**: Modified to accept only `fullName`, `email`, and `password`
- **Value Objects**: 
  - Added `FullName` record with validation
  - Removed `UserRole` completely

### Interface Layer Changes

- **CreateUserResource**: Updated to include only essential fields with validations
- **UserResource**: Simplified to include only `id`, `fullName`, and `email`
- **Assemblers**: Updated to work with simplified field structure

### Database Changes

The database table structure has been simplified:

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

## API Structure

### Create User

**POST** `/api/v1/users`

```json
{
  "fullName": "Juan Pérez García",
  "email": "juan.perez@example.com",
  "password": "SecurePass123!"
}
```

**Response:**
```json
{
  "id": 1,
  "fullName": "Juan Pérez García",
  "email": "juan.perez@example.com"
}
```

## Validation Rules

- **fullName**: Required, minimum 2 characters
- **email**: Required, valid email format
- **password**: Required, minimum 8 characters, must contain letters, numbers, and symbols

## Technologies Used

- Spring Boot
- Spring Data JPA
- Jakarta Validation
- Maven
- Domain-Driven Design (DDD)

## Users Context

### Domain Model

#### User Aggregate
- **User**: Main aggregate representing a user in the system
- **FullName**: Value object for user's complete name
- **UserRole**: Value object for user roles with ID-based identification:
  - ID `0`: GANADERO (Rancher)
  - ID `1`: VETERINARIO (Veterinarian)
- **Password**: Value object with validation rules
- **EmailAddress**: Value object for email validation

#### Commands and Queries
- **CreateUserCommand**: Command to create a new user
- **GetAllUsersQuery**: Query to retrieve all users
- **GetUserByIdQuery**: Query to retrieve a user by ID

### Business Validations

#### Password Requirements
- Minimum 8 characters
- Must contain letters, numbers, and symbols
- Cannot be null or blank

#### Role Validation
- Must be either 0 (GANADERO) or 1 (VETERINARIO)
- Cannot be null

#### Email Validation
- Must be a valid email format
- Must be unique in the system
- Cannot be null or blank

#### Full Name Validation
- Cannot be null or blank
- Must be at least 2 characters long

### REST API Endpoints

#### Create User
```http
POST /api/v1/users
Content-Type: application/json

{
  "fullName": "Juan Pérez",
  "email": "juan.perez@example.com",
  "password": "SecurePass123!",
  "roleId": 0
}
```

#### Get All Users
```http
GET /api/v1/users
```

#### Get User by ID
```http
GET /api/v1/users/{id}
```

### Response Format
```json
{
  "id": 1,
  "fullName": "Juan Pérez",
  "email": "juan.perez@example.com",
  "roleId": 0,
  "roleName": "GANADERO"
}
```

## Database Configuration

The application is configured to use MySQL database with the following settings:

- **Database**: `agrodigital_db`
- **Port**: 3306
- **Username**: `root`
- **Password**: `password`
- **Naming Strategy**: Snake case with pluralized table names

### Table Structure

The `users` table will be created with the following structure:
- `id` (BIGINT, Primary Key, Auto Increment)
- `full_name` (VARCHAR)
- `email` (VARCHAR, Unique)
- `password` (VARCHAR)
- `role_id` (INTEGER)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

### Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd AgroDigital-Backend
   ```

2. **Configure Database**
   - Create a MySQL database named `agrodigital_db`
   - Update database credentials in `src/main/resources/application.properties` if needed

3. **Build the project**
   ```bash
   mvn clean compile
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**
   - API Base URL: `http://localhost:8080/api/v1`
   - Swagger Documentation: `http://localhost:8080/swagger-ui.html`

## API Documentation

Once the application is running, you can access the interactive API documentation at:
`http://localhost:8080/swagger-ui.html`

## Technologies Used

- **Spring Boot 3.x**: Main framework
- **Spring Data JPA**: Data persistence
- **MySQL**: Database
- **Maven**: Build tool
- **Jakarta Validation**: Input validation
- **Swagger/OpenAPI**: API documentation

## Example Usage

### Creating a Rancher (GANADERO)
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Carlos Rodriguez",
    "email": "carlos.rodriguez@ranch.com",
    "password": "SecurePass123!",
    "roleId": 0
  }'
```

### Creating a Veterinarian (VETERINARIO)
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Dr. Maria Lopez",
    "email": "maria.lopez@vet.com",
    "password": "VetPass456#",
    "roleId": 1
  }'
```

## Next Steps

1. Implement authentication and authorization
2. Add more business contexts (Animals, Treatments, etc.)
3. Implement event-driven communication between contexts
4. Add comprehensive testing suite
5. Implement logging and monitoring