# Tiradaus API

Tiradaus is a user authentication and account management API built with **Spring Boot**, **Hibernate/JPA**, and **PostgreSQL**, packaged with **Docker** for easy deployment. It supports **JWT-based authentication** including login and user registration, and provides built-in OpenAPI/Swagger documentation.

---

## 🧱 Tech Stack

| Component        | Technology |
|-----------------|------------|
| Backend         | Java 21, Spring Boot 3 |
| Authentication  | JWT |
| Storage         | PostgreSQL 18 |
| Build System    | Gradle |
| Containerization| Docker + Docker Compose |
| API Docs        | springdoc-openapi (Swagger UI) |

---

## 🚀 Getting Started

### Requirements
- Docker & Docker Compose installed
- Free port **8080** for the API

### Start the stack

```bash
docker compose up -d
```

Check logs:
```bash
docker compose logs -f api_rest
```

API should be reachable at:
http://localhost:8080


### 🔑 Demo Credentials
Username	Password	Role
admin	admin1234	ADMIN

You can also self-register additional accounts via the /api/auth/register endpoint.

### 📚 API Endpoints

Swagger UI:
```bash
http://localhost:8080/swagger
```

Login:
```http
POST /api/auth/login
```

Example request:
```json
{
  "username": "admin",
  "password": "admin1234"
}
```
-----------------------------------------------------

Register:
```http
POST /api/auth/register
```

Example request:
```json
{
  "username": "jdoe",
  "email": "jdoe@example.com",
  "password": "P@ssw0rd!",
  "firstName": "John",
  "lastName": "Doe",
  "roleId": 2
}
```