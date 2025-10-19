# 🏆 Deportes UM API

Proyecto desarrollado como ejercicio técnico para la **convocatoria de octubre 2025**, correspondiente al puesto de desarrollo backend.  
Implementa una **API REST con Spring Boot** para la gestión de competiciones deportivas, equipos y generación automática de partidos.

---

## 🚀 Ejecución del proyecto

### 🧩 Requisitos previos
- **Java 11 o 17**
- **Maven 3.8+**
- **IntelliJ IDEA**, Eclipse o VS Code (opcional)
- Conexión local (usa base de datos en memoria H2)

---

### ▶️ Ejecución

1. Clona o descomprime el proyecto  
   ```bash
   git clone https://github.com/<tu_usuario>/deportes-um.git
   ```
   o descarga el ZIP y ábrelo en IntelliJ IDEA.

2. Ejecuta:
   ```bash
   mvn spring-boot:run
   ```

3. Accede a:
   - 🌐 **Swagger UI:** [http://localhost:8080/swagger](http://localhost:8080/swagger)
   - 🗄️ **Consola H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - JDBC URL: `jdbc:h2:mem:deportesdb`
     - Usuario: `sa`
     - Contraseña: *(vacía)*

---

## 🧱 Estructura del proyecto

```
src/
 ├── main/
 │   ├── java/com/universidadmurcia/deportes/
 │   │   ├── api/              → Controladores REST
 │   │   ├── application/      → Servicios y lógica de negocio
 │   │   ├── domain/           → Entidades, repositorios y excepciones
 │   │   └── infrastructure/   → Configuración e inicialización (DatabaseSeeder)
 │   └── resources/
 │       └── application.yml   → Configuración H2 y Swagger
 └── test/
     └── java/...              → Tests unitarios (JUnit)
```

---

## ⚙️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Data JPA**
- **H2 Database (in-memory)**
- **Lombok**
- **Swagger / OpenAPI (SpringDoc)**
- **JUnit 5**
- **Maven**

---

## 📚 Modelo de datos

**Entidades principales:**
- `Competition` → representa una competición (nombre, deporte, fechas, número de pistas…)
- `Team` → equipos inscritos en una competición
- `Match` → partidos generados (con asignación de equipos, pista y fecha)

**Relaciones:**
- Una `Competition` tiene muchos `Team` (1:N)
- Una `Competition` tiene muchos `Match` (1:N)
- Cada `Match` enfrenta dos `Team` (local y visitante)

---

## 🧩 Endpoints principales

| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| **GET** | `/competitions` | Listar todas las competiciones |
| **POST** | `/competitions` | Crear una nueva competición |
| **GET** | `/competitions/{id}/teams` | Listar equipos de una competición |
| **POST** | `/competitions/{id}/teams` | Registrar un nuevo equipo |
| **POST** | `/competitions/{id}/matches/generate` | Generar la primera jornada |
| **GET** | `/competitions/{id}/matches` | Listar partidos generados |
| **GET** | `/competitions/{id}/matches/unassigned` | Mostrar equipos no asignados |

---

## 🧪 Pruebas con Postman

Se incluyen los archivos:

```
postman/
 ├── deportes-um.postman_collection.json
 └── deportes-um-localhost.postman_environment.json
```

### Importar en Postman:
1. Abre Postman → **Import**  
2. Selecciona ambos archivos  
3. Activa el entorno **"Deportes UM Localhost"**  
4. Ejecuta la colección **"Deportes UM API"** en orden

---

## 🧠 Lógica de generación de partidos

La generación de la primera jornada (`POST /matches/generate`) sigue estas reglas:
- Cada partido se asigna a una pista disponible, respetando el número máximo (`numPistas`) de la competición.  
- Se generan enfrentamientos por parejas en orden de inscripción.  
- Si hay equipos impares, uno queda libre (“bye”) y aparecerá en la consulta `/matches/unassigned`.  
- Los partidos se asignan al primer día reservado de la competición.

### Ejemplo:
```
Equipos: A, B, C, D, E
Pistas: 2
→ Jornada 1:
  - Partido 1: A vs B (Pista 1)
  - Partido 2: C vs D (Pista 2)
  - Equipo E: sin asignar
```

---

## ⚠️ Manejo de errores

| Código | Situación |
|--------|------------|
| **404 Not Found** | Competición no encontrada |
| **409 Conflict** | Intento de registrar un equipo duplicado |
| **500 Internal Server Error** | Error genérico no controlado |

Los errores se devuelven en formato JSON, por ejemplo:
```json
{
  "error": "El equipo ya está registrado en esta competición"
}
```

---

## 🧩 Tests unitarios

Ejemplo incluido:
- `TeamServiceTest` → Verifica registro y persistencia de equipos.

Se pueden ejecutar con:
```bash
mvn test
```

---

## 🧱 Extensiones futuras

- Generar varias jornadas automáticamente.
- Añadir persistencia real (PostgreSQL / MySQL).
- Validaciones de entrada (`@Valid` + DTOs).
- Autenticación de usuarios.
- Interfaz web o cliente React para gestión visual.

---

## 🧭 Arquitectura del sistema

```
Cliente (Postman / Swagger UI)
       ↓
API Layer (Controllers REST)
       ↓
Application Layer (Servicios)
       ↓
Domain Layer (Entidades y Repositorios)
       ↓
Infrastructure (Base de datos H2)
```

> 🔍 **Nota:**  
> La arquitectura sigue un patrón *Clean Architecture / DDD simplificado*, donde las dependencias fluyen hacia el dominio.  
> Las capas superiores (API, Application) dependen de las inferiores (Domain, Infrastructure), pero nunca al revés.

---

## 👨‍💻 Autor
Pablo Menchon Sanchez  
Desarrollador Backend Java
Convocatoria técnica – Octubre 2025

---

## 🪶 Licencia
Este proyecto se distribuye únicamente con fines de evaluación técnica.  
No se autoriza su redistribución ni uso comercial sin permiso del autor.
