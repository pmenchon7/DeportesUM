# 🧱 Arquitectura del Sistema  
**Proyecto:** Deportes UM API  
**Convocatoria:** Octubre 2025  
**Autor:** Pablo Menchon Sanchez

---

## 🎯 Objetivo

El sistema implementa una arquitectura en capas para la gestión de competiciones deportivas universitarias, diseñada con **principios de separación de responsabilidades** y **fácil extensibilidad**.  
Cada capa tiene un propósito específico, lo que permite mantener el código limpio, escalable y testeable.

---

## 🧩 Diagrama general de arquitectura

               ┌────────────────────────┐
               │        CLIENTE         │
               │ (Postman / Swagger UI) │
               └────────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────┐
                 │      API Layer    │
                 │ (Controllers REST)│
                 └──────────────────┘
                            │
                            ▼
                 ┌──────────────────┐
                 │ Application Layer│
                 │ (Services)       │
                 └──────────────────┘
                            │
                            ▼
                 ┌──────────────────┐
                 │   Domain Layer   │
                 │ (Entities + Repo)│
                 └──────────────────┘
                            │
                            ▼
                 ┌──────────────────┐
                 │ Infrastructure   │
                 │ (H2 Database)    │
                 └──────────────────┘

---

## 🧭 Descripción de las capas

| Capa | Contenido | Responsabilidad |
|------|------------|----------------|
| **API Layer** | Controladores REST (`CompetitionController`, `TeamController`, `MatchController`) | Exponer endpoints HTTP y convertir las peticiones/respuestas a JSON. |
| **Application Layer** | Servicios (`CompetitionService`, `TeamService`, `MatchService`) | Contiene la lógica de negocio. Aplica reglas y coordina las operaciones entre repositorios. |
| **Domain Layer** | Entidades y repositorios JPA (`Competition`, `Team`, `Match`) | Define el modelo del dominio y las operaciones básicas de persistencia. |
| **Infrastructure Layer** | Configuración técnica (`DatabaseSeeder`, conexión H2) | Adaptadores e implementación de persistencia. Sin lógica de negocio. |

---

## 🔁 Flujo de ejecución típico

1. El cliente (Postman o Swagger UI) envía una petición HTTP, por ejemplo:  
   `POST /competitions/1/teams`
2. El **Controller (API Layer)** recibe la petición y la delega al **Service (Application Layer)**.  
3. El **Service** valida la lógica (por ejemplo, no duplicar equipos) y llama al **Repository** correspondiente.  
4. El **Repository (Domain Layer)** persiste los datos en la base **H2**.  
5. El resultado se devuelve al cliente en formato **JSON**.

---

## 🌐 Diagrama visual (Mermaid)

Para generar una imagen del flujo en [Mermaid Live](https://mermaid.live/), copia este código:

```mermaid
flowchart TD

A["Cliente<br/>(Postman / Swagger UI)"] --> B["API Layer<br/>(Controllers REST)"]
B --> C["Application Layer<br/>(Servicios)"]
C --> D["Domain Layer<br/>(Entidades y Repositorios)"]
D --> E["Infrastructure<br/>(Base de datos H2)"]

classDef layer fill:#f4f4f4,stroke:#333,stroke-width:1px,border-radius:6px;
class A,B,C,D,E layer;

