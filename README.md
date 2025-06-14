# AgroDigital Backend

This is the backend for the AgroDigital application, built with Spring Boot and following Domain-Driven Design (DDD) principles.

## Recent Changes

### Complete Application Implementation

- **Multi-Context Architecture**: Implemented complete DDD architecture with Users, Animals, Events, Incomes, and Expenses contexts
- **CRUD Operations**: Full Create, Read, Update, and Delete operations for all entities
- **Profile Management**: Added profile image URL support and PATCH endpoint for user updates
- **API Documentation**: Updated to "ACME AgroDigital Backend API" with proper descriptions

### Key Features Implemented

#### User Management
- **User Profile Updates**: PATCH endpoint for updating user information including profile image URL
- **Profile Image Support**: Users can now set profile images via URL
- **Comprehensive Validation**: Enhanced validation for all user fields

#### Multi-Domain Implementation
- **Animals Context**: Complete animal management with creator tracking
- **Events Context**: Event management system
- **Incomes Context**: Financial income tracking
- **Expenses Context**: Financial expense tracking
- **DELETE Operations**: Implemented delete functionality across all contexts

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

## Domain Contexts

### Users Context
#### Domain Model
##### User Aggregate

- **User**: Main aggregate representing a user in the system with the following fields:
  - **id**: Unique identifier (Long)
  - **name**: User's full name (String)
  - **email**: User's email address (String)
  - **password**: User's password (String)
  - **role**: User's role (String)
  - **profileImage**: URL for user's profile image (String, optional)
- **FullName**: Value object for user's complete name (internal use)
- **Password**: Value object with validation rules
- **EmailAddress**: Value object for email validation

##### Commands and Queries
- **CreateUserCommand**: Command to create a new user
- **UpdateUserCommand**: Command to update user information (PATCH support)
- **GetAllUsersQuery**: Query to retrieve all users
- **GetUserByIdQuery**: Query to retrieve a user by ID

### Animals Context
#### Domain Model
##### Animal Aggregate

- **Animal**: Main aggregate representing an animal with the following fields:
  - **id**: Unique identifier (Long)
  - **name**: Animal's name (String)
  - **species**: Animal species (String)
  - **breed**: Animal breed (String)
  - **birthDate**: Animal's birth date (LocalDate)
  - **weight**: Animal's weight (BigDecimal)
  - **createdBy**: ID of the user who created the record (Long)

##### Commands and Queries
- **CreateAnimalCommand**: Command to create a new animal
- **DeleteAnimalCommand**: Command to delete an animal
- **GetAllAnimalsQuery**: Query to retrieve all animals
- **GetAnimalByIdQuery**: Query to retrieve an animal by ID
- **GetAnimalsByCreatorQuery**: Query to retrieve animals by creator

### Events Context
#### Domain Model
##### Event Aggregate

- **Event**: Main aggregate representing an event with the following fields:
  - **id**: Unique identifier (Long)
  - **title**: Event title (String)
  - **description**: Event description (String)
  - **eventDate**: Date of the event (LocalDate)
  - **userId**: ID of the user associated with the event (Long)
  - **userName**: Name of the user associated with the event (String)

##### Commands and Queries
- **CreateEventCommand**: Command to create a new event
- **DeleteEventCommand**: Command to delete an event
- **GetAllEventsQuery**: Query to retrieve all events
- **GetEventByIdQuery**: Query to retrieve an event by ID

### Incomes Context
#### Domain Model
##### Income Aggregate

- **Income**: Main aggregate representing an income with the following fields:
  - **id**: Unique identifier (Long)
  - **amount**: Income amount (BigDecimal)
  - **description**: Income description (String)
  - **incomeDate**: Date of the income (LocalDate)
  - **userId**: ID of the user associated with the income (Long)
  - **userName**: Name of the user associated with the income (String)

##### Commands and Queries
- **CreateIncomeCommand**: Command to create a new income
- **DeleteIncomeCommand**: Command to delete an income
- **GetAllIncomesQuery**: Query to retrieve all incomes
- **GetIncomeByIdQuery**: Query to retrieve an income by ID

### Expenses Context
#### Domain Model
##### Expense Aggregate

- **Expense**: Main aggregate representing an expense with the following fields:
  - **id**: Unique identifier (Long)
  - **amount**: Expense amount (BigDecimal)
  - **description**: Expense description (String)
  - **expenseDate**: Date of the expense (LocalDate)
  - **userId**: ID of the user associated with the expense (Long)
  - **userName**: Name of the user associated with the expense (String)

##### Commands and Queries
- **CreateExpenseCommand**: Command to create a new expense
- **DeleteExpenseCommand**: Command to delete an expense
- **GetAllExpensesQuery**: Query to retrieve all expenses
- **GetExpenseByIdQuery**: Query to retrieve an expense by ID

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

## REST API Endpoints

### Users Endpoints

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

#### Update User (Profile Configuration)
```http
PATCH /api/v1/users/{id}
Content-Type: application/json

{
  "name": "Juan Pérez García",
  "email": "juan.perez.garcia@example.com",
  "password": "NewSecurePass123!",
  "role": "rancher",
  "profileImage": "https://example.com/profile-image.jpg"
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

### Animals Endpoints

#### Create Animal
```http
POST /api/v1/animals
Content-Type: application/json

{
  "name": "Bessie",
  "species": "Cow",
  "breed": "Holstein",
  "birthDate": "2020-05-15",
  "weight": 450.5,
  "createdBy": 1
}
```

#### Get All Animals
```http
GET /api/v1/animals
```

#### Get Animal by ID
```http
GET /api/v1/animals/{id}
```

#### Get Animals by Creator
```http
GET /api/v1/animals/creator/{createdBy}
```

#### Delete Animal
```http
DELETE /api/v1/animals/{id}
```

### Events Endpoints

#### Create Event
```http
POST /api/v1/events
Content-Type: application/json

{
  "title": "Vaccination Schedule",
  "description": "Annual vaccination for cattle",
  "eventDate": "2024-06-15",
  "userId": 1,
  "userName": "Juan Pérez"
}
```

#### Get All Events
```http
GET /api/v1/events
```

#### Get Event by ID
```http
GET /api/v1/events/{id}
```

#### Delete Event
```http
DELETE /api/v1/events/{id}
```

### Incomes Endpoints

#### Create Income
```http
POST /api/v1/incomes
Content-Type: application/json

{
  "amount": 1500.00,
  "description": "Milk sales",
  "incomeDate": "2024-01-15",
  "userId": 1,
  "userName": "Juan Pérez"
}
```

#### Get All Incomes
```http
GET /api/v1/incomes
```

#### Get Income by ID
```http
GET /api/v1/incomes/{id}
```

#### Delete Income
```http
DELETE /api/v1/incomes/{id}
```

### Expenses Endpoints

#### Create Expense
```http
POST /api/v1/expenses
Content-Type: application/json

{
  "amount": 250.00,
  "description": "Feed purchase",
  "expenseDate": "2024-01-10",
  "userId": 1,
  "userName": "Juan Pérez"
}
```

#### Get All Expenses
```http
GET /api/v1/expenses
```

#### Get Expense by ID
```http
GET /api/v1/expenses/{id}
```

#### Delete Expense
```http
DELETE /api/v1/expenses/{id}
```

### Patients Endpoints

#### Create Patient
```http
POST /api/v1/patients
Content-Type: application/json

{
  "name": "Bessie",
  "type": "Vaca Holstein",
  "age": "3 años",
  "gender": "Hembra",
  "healthIssues": "problemas respiratorios",
  "owner": "Juan Pérez",
  "lastVisit": "2024-01-15",
  "nextVisit": "2024-02-15",
  "image": "https://example.com/cow-image.jpg",
  "observations": "Animal en buen estado general, requiere seguimiento",
  "createdBy": 2
}
```

#### Get All Patients
```http
GET /api/v1/patients
```

#### Get Patient by ID
```http
GET /api/v1/patients/{id}
```

#### Delete Patient
```http
DELETE /api/v1/patients/{id}
```

### Response Formats

#### User Response
```json
{
  "id": 1,
  "name": "Juan Pérez",
  "email": "juan.perez@example.com",
  "password": "SecurePass123!",
  "role": "rancher",
  "profileImage": "https://example.com/profile-image.jpg"
}
```

#### Animal Response
```json
{
  "id": 1,
  "name": "Bessie",
  "species": "Cow",
  "breed": "Holstein",
  "birthDate": "2020-05-15",
  "weight": 450.5,
  "createdBy": 1
}
```

#### Event Response
```json
{
  "id": 1,
  "title": "Vaccination Schedule",
  "description": "Annual vaccination for cattle",
  "eventDate": "2024-06-15",
  "userId": 1,
  "userName": "Juan Pérez"
}
```

#### Income Response
```json
{
  "id": 1,
  "amount": 1500.00,
  "description": "Milk sales",
  "incomeDate": "2024-01-15",
  "userId": 1,
  "userName": "Juan Pérez"
}
```

#### Expense Response
```json
{
  "id": 1,
  "amount": 250.00,
  "description": "Feed purchase",
  "expenseDate": "2024-01-10",
  "userId": 1,
  "userName": "Juan Pérez"
}
```

#### Patient Response
```json
{
  "id": 1,
  "name": "Bessie",
  "type": "Vaca Holstein",
  "age": "3 años",
  "gender": "Hembra",
  "healthIssues": "problemas respiratorios",
  "owner": "Juan Pérez",
  "lastVisit": "2024-01-15",
  "nextVisit": "2024-02-15",
  "image": "https://example.com/cow-image.jpg",
  "observations": "Animal en buen estado general, requiere seguimiento",
  "createdBy": 2
}
```

## Database Configuration

The application is configured to use MySQL database with the following settings:

- **Database**: `agrodigital_db`
- **Port**: 3306
- **Username**: `root`
- **Password**: `password`
- **Naming Strategy**: Snake case with pluralized table names

### Database Tables

#### Users Table
- `id` (BIGINT, Primary Key, Auto Increment)
- `full_name` (VARCHAR)
- `email` (VARCHAR, Unique)
- `password` (VARCHAR)
- `role` (VARCHAR)
- `profile_image_url` (VARCHAR, Nullable)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

#### Animals Table
- `id` (BIGINT, Primary Key, Auto Increment)
- `name` (VARCHAR)
- `species` (VARCHAR)
- `breed` (VARCHAR)
- `birth_date` (DATE)
- `weight` (DECIMAL)
- `created_by` (BIGINT)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

#### Events Table
- `id` (BIGINT, Primary Key, Auto Increment)
- `title` (VARCHAR)
- `description` (TEXT)
- `event_date` (DATE)
- `user_id` (BIGINT)
- `user_name` (VARCHAR)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

#### Incomes Table
- `id` (BIGINT, Primary Key, Auto Increment)
- `amount` (DECIMAL)
- `description` (VARCHAR)
- `income_date` (DATE)
- `user_id` (BIGINT)
- `user_name` (VARCHAR)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

#### Expenses Table
- `id` (BIGINT, Primary Key, Auto Increment)
- `amount` (DECIMAL)
- `description` (VARCHAR)
- `expense_date` (DATE)
- `user_id` (BIGINT)
- `user_name` (VARCHAR)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

#### Patients Table
- `id` (BIGINT, Primary Key, Auto Increment)
- `name` (VARCHAR)
- `type` (VARCHAR) - Options: Vaca Holstein, Toro Angus, Cabra Alpina, Oveja Merino, Caballo Andaluz
- `age` (VARCHAR)
- `gender` (VARCHAR) - Options: Macho, Hembra
- `health_issues` (VARCHAR) - Options: problemas respiratorios, cojera leve, infección, otro
- `owner` (VARCHAR)
- `last_visit` (DATE)
- `next_visit` (DATE)
- `image` (VARCHAR, Nullable)
- `observations` (TEXT, Nullable)
- `created_by` (BIGINT)
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