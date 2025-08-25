# 📝 Notes App

Aplikasi **Notes App** berbasis Spring Boot + PostgreSQL.  
Dijalankan menggunakan **Docker Compose** sehingga mudah di-setup tanpa konfigurasi manual.

---

## 🚀 Tech Stack
- Java 17
- Spring Boot ( Docs Swagger & Security JWT )
- PostgreSQL
- Docker & Docker Compose

---

## ⚙️ Setup Project

### 1. Clone Repository
```bash
git clone https://github.com/bagya-rmdn/notes-app.git
cd notes-app
```

### 2. Build & Run dengan Docker Compose
```bash
docker-compose up --build
```

### 3. Akses Database
*Note: Jika sudah selesai build docker akses database sebagai berikut*
 - Host: host.docker.internal
 - Port: 5433 
 - User: postgres 
 - Password: postgres 
 - Database: notes_db

### 4. Jika sudah berhasil konek create table
```bash
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE notes (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       content TEXT,
                       is_archived BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

# 📝 API Endpoints ( API Docs )
 - http://localhost:8080/swagger-ui.html
 - GET	/api/notes	Get all notes 
 - POST	/api/notes	Create new note 
 - PUT	/api/notes/{id}	Update note by id 
 - DELETE	/api/notes/{id}	Delete note by id
 - GET	/api/notes/{id}	Get note by id
 - POST /api/auth/signup Register new user
 - POST /api/auth/signin Login user
