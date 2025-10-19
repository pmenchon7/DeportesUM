# 🧠 Decisiones Técnicas y de Diseño  
**Proyecto:** Deportes UM API  
**Convocatoria:** Octubre 2025  
**Autor:** Pablo Menchon Sanchez  

---

## 🎯 Objetivo
Diseñar e implementar una API REST con **Spring Boot** que permita:
- Registrar competiciones deportivas universitarias.
- Inscribir equipos en dichas competiciones.
- Generar automáticamente los partidos de la primera jornada.
- Consultar equipos, partidos y los equipos no asignados.

---

## 🏗️ Arquitectura general

### 📂 Capas principales

| Capa | Descripción |
|------|--------------|
| **API** (`/api`) | Controladores REST. Gestionan las peticiones HTTP y devuelven respuestas JSON. |
| **Application** (`/application`) | Contiene la lógica de negocio. Implementa los servicios para competiciones, equipos y partidos. |
| **Domain** (`/domain`) | Define el modelo de datos, entidades JPA, repositorios e interfaces. Representa el “núcleo” del sistema. |
| **Infrastructure** (`/infrastructure`) | Configuración del proyecto, inicialización de datos (seeder) y adaptadores técnicos (persistence, H2, etc.). |

**Patrón utilizado:** *Arquitectura en capas con principios de DDD (Domain Driven Design) simplificados.*

---

## 🧩 Diseño de base de datos

### 📘 Modelo entidad–relación (ER)

Competition (1) ───< (N) Team
Competition (1) ───< (N) Match
Team (1) ───< (N) Match (como local o visitante)


### 🔹 Tablas principales
- **Competition**
  - id (PK)
  - nombre
  - deporte
  - fecha_inicio
  - fecha_fin
  - num_pistas
  - dias_reservados (almacenado como lista de fechas)
  
- **Team**
  - id (PK)
  - nombre
  - competition_id (FK)

- **Match**
  - id (PK)
  - competition_id (FK)
  - equipo_local_id (FK)
  - equipo_visitante_id (FK)
  - fecha
  - pista

**Restricciones:**
- Un equipo no puede repetirse en la misma competición (`unique(nombre, competition_id)`).
- Cada partido pertenece a una sola competición.

---

## ⚙️ Lógica de generación de partidos

### Algoritmo de la primera jornada
1. Se obtienen todos los equipos de la competición.
2. Se ordenan por nombre o por orden de registro.
3. Se agrupan en parejas `(equipoA, equipoB)` para crear partidos.
4. Cada partido se asigna:
   - A una **pista** disponible (hasta `numPistas`).
   - A una **fecha** correspondiente al primer día reservado de la competición.
5. Si hay un número impar de equipos, el último queda “sin asignar” y se devuelve en `/matches/unassigned`.

### Ejemplo:

Equipos: A, B, C, D, E
Pistas: 2
→ Jornada 1:

Partido 1: A vs B (Pista 1)

Partido 2: C vs D (Pista 2)

Equipo E: sin asignar

---

## 🧰 Tecnologías seleccionadas

| Tecnología | Motivo |
|-------------|--------|
| **Spring Boot** | Rapidez de desarrollo y configuración mínima. |
| **Spring Data JPA** | Simplifica la persistencia con repositorios automáticos. |
| **H2 Database (en memoria)** | Permite pruebas sin instalación externa. |
| **Lombok** | Reduce el código repetitivo (getters, setters, builders). |
| **Swagger (SpringDoc)** | Documentación automática de la API REST. |
| **JUnit 5** | Framework moderno para pruebas unitarias. |

---

## 🧠 Justificación de decisiones

- **Arquitectura en capas** → mantiene separación de responsabilidades y facilita escalabilidad.
- **Entidades y repositorios JPA** → abstraen la lógica de persistencia, permitiendo migrar fácilmente a una base de datos real.
- **Servicios desacoplados** → cada dominio (competición, equipo, partido) tiene su propia lógica independiente.
- **H2 + Seeder** → garantiza que el proyecto arranque funcional y sea evaluable sin configuración externa.
- **Swagger** → demuestra buenas prácticas de documentación y facilita la revisión.

---

## 🧩 Posibles mejoras futuras

- Generación de jornadas múltiples.
- Persistencia en **PostgreSQL** o **MySQL**.
- Validaciones de entrada con `@Valid` y DTOs específicos.
- Control de usuarios (autenticación/autorización).
- Frontend web o aplicación móvil para gestión visual.
- Pruebas de integración con MockMvc o Testcontainers.

---

## 🧾 Conclusión

El proyecto implementa de forma completa y funcional los requisitos del ejercicio técnico.  
La arquitectura, claridad del código y documentación facilitan su mantenimiento y ampliación futura.

---

