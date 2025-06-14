# AgroDigital Backend

This is the backend for the AgroDigital application, built with Spring Boot and following Domain-Driven Design (DDD) principles.

## Recent Changes

### User Structure Simplification

- **Modified User Structure**: Updated user fields to include `name`, `email`, `password`, and `role` as simple string fields.
- **Simplified Field Names**: Changed from `fullName` to `name` for consistency with frontend requirements.
- **Deleted `PersonName.java`**: The old `PersonName` value object has been removed from the users context.
- **Removed Learning Context**: Completely eliminated the learning context including Students, Courses, and Enrollments to focus on core functionality.

### Key Changes Made

#### Domain Model Updates

- **User Aggregate**: Now includes `name`, `email`, `password`, and `role` fields
- **CreateUserCommand**: Modified to accept `name`, `email`, `password`, and `role`
- Added `role` as a simple string field
- Maintained `FullName` value object for internal consistency

#### API Changes

- **CreateUserResource**: Updated to include only essential fields with validations
- **UserResource**: Updated to include `id`, `name`, `email`, `password`, and `role`
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
  "name": "Juan Pérez García",
  "email": "juan.perez@example.com",
  "password": "SecurePass123!",
  "role": "veterinarian"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "Juan Pérez García",
  "email": "juan.perez@example.com",
  "password": "SecurePass123!",
  "role": "veterinarian"
}
```

#### Field Validations
- **name**: Required, minimum 2 characters
- **email**: Required, valid email format
- **password**: Required, minimum 8 characters, must contain letters, numbers, and symbols
- **role**: Required, string value

## Technologies Used

- Spring Boot
- Spring Data JPA
- Jakarta Validation
- Maven
- Domain-Driven Design (DDD)

## Users Context
### Domain Model
#### User Aggregate

- **User**: Main aggregate representing a user in the system with the following fields:
  - **id**: Unique identifier (Long)
  - **name**: User's full name (String)
  - **email**: User's email address (String)
  - **password**: User's password (String)
  - **role**: User's role (String)
- **FullName**: Value object for user's complete name (internal use)
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
  "name": "Juan Pérez",
  "email": "juan.perez@example.com",
  "password": "SecurePass123!",
  "role": "rancher"
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
  "name": "Juan Pérez",
  "email": "juan.perez@example.com",
  "password": "SecurePass123!",
  "role": "rancher"
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
- `role` (VARCHAR)
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

### Creating a Rancher
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Carlos Rodriguez",
    "email": "carlos.rodriguez@ranch.com",
    "password": "SecurePass123!",
    "role": "rancher"
  }'
```

### Creating a Veterinarian
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dr. Maria Lopez",
    "email": "maria.lopez@vet.com",
    "password": "VetPass456#",
    "role": "veterinarian"
  }'
```

## Next Steps

1. Implement authentication and authorization
2. Add more business contexts (Animals, Treatments, etc.)
3. Implement event-driven communication between contexts
4. Add comprehensive testing suite
5. Implement logging and monitoring