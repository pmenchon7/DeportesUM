# Deportes UM - API

Proyecto desarrollado para la convocatoria técnica de octubre 2025.  
Implementa una API REST con Spring Boot para la gestión de competiciones deportivas.

---

### Ejecución:
```bash
mvn spring-boot:run
```

### Requisitos
- Java 11 o 17
- Maven 3.8+
- IntelliJ IDEA o VS Code (opcional)


Endpoints principales:
- POST /competitions
- GET  /competitions
- POST /competitions/{id}/teams
- GET  /competitions/{id}/teams
- POST /competitions/{id}/matches/generate
- GET  /competitions/{id}/matches
- GET  /competitions/{id}/matches/unassigned

Swagger UI: http://localhost:8080/swagger
H2 Console:  http://localhost:8080/h2-console (jdbc:h2:mem:deportesdb)
