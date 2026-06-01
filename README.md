# 🌍 Rutas Turísticas — Proyecto Final

Aplicación fullstack para gestión de rutas turísticas.

---

## 🗂️ Estructura del Proyecto

```
rutas-turisticas/
├── backend/                        ← Spring Boot + JPA
│   ├── pom.xml
│   └── src/main/java/com/turisticas/rutas/
│       ├── RutasApplication.java       (clase principal)
│       ├── CorsConfig.java             (configuración CORS)
│       ├── DataInitializer.java        (datos de prueba)
│       ├── model/
│       │   ├── Tipo.java
│       │   ├── Pais.java
│       │   ├── Ciudad.java
│       │   ├── Ruta.java
│       │   └── Parada.java
│       ├── repository/
│       │   ├── TipoRepository.java
│       │   ├── PaisRepository.java
│       │   ├── CiudadRepository.java
│       │   ├── RutaRepository.java     (findByCiudadId)
│       │   └── ParadaRepository.java   (findByRutaIdOrderByOrdenAsc)
│       └── controller/
│           ├── TipoController.java
│           ├── PaisController.java
│           ├── CiudadController.java
│           ├── RutaController.java     (+GET /ciudad/{id})
│           └── ParadaController.java   (+GET /ruta/{id})
│
└── frontend/                       ← AngularJS + HTML + CSS
    ├── index.html                      (vista principal)
    ├── css/
    │   └── styles.css
    └── js/
        ├── app.js                      (módulo Angular)
        ├── services.js                 (servicios HTTP)
        └── controllers.js             (lógica de la app)
```

---

## ▶️ Cómo ejecutar

### Backend (Spring Boot)

**Requisitos:** Java 17+, Maven 3.8+

```bash
cd backend
mvn spring-boot:run
```

El servidor arranca en **http://localhost:8080**

- Swagger UI:   http://localhost:8080/swagger-ui/index.html
- H2 Console:  http://localhost:8080/h2-console  (JDBC URL: `jdbc:h2:mem:rutasdb`, user: `sa`)

### Frontend (AngularJS)

Abrir `frontend/index.html` directamente en el navegador **o** servirlo con un servidor local:

```bash
# Opción 1: Python
cd frontend
python -m http.server 5500

# Opción 2: VS Code Live Server
# Clic derecho en index.html → Open with Live Server
```

Acceder en: **http://localhost:5500**

---

## 🔌 Endpoints de la API

| Método | URL                              | Descripción                        |
|--------|----------------------------------|------------------------------------|
| GET    | /api/tipos/                      | Listar todos los tipos             |
| POST   | /api/tipos/                      | Crear tipo                         |
| PUT    | /api/tipos/                      | Actualizar tipo                    |
| DELETE | /api/tipos/{id}                  | Eliminar tipo                      |
| GET    | /api/paises/                     | Listar países                      |
| POST   | /api/paises/                     | Crear país                         |
| PUT    | /api/paises/                     | Actualizar país                    |
| DELETE | /api/paises/{id}                 | Eliminar país                      |
| GET    | /api/ciudades/                   | Listar ciudades                    |
| POST   | /api/ciudades/                   | Crear ciudad                       |
| PUT    | /api/ciudades/                   | Actualizar ciudad                  |
| DELETE | /api/ciudades/{id}               | Eliminar ciudad                    |
| GET    | /api/rutas/                      | Listar todas las rutas             |
| GET    | /api/rutas/ciudad/{idCiudad}     | Rutas de una ciudad ⭐              |
| POST   | /api/rutas/                      | Crear ruta                         |
| PUT    | /api/rutas/                      | Actualizar ruta                    |
| DELETE | /api/rutas/{id}                  | Eliminar ruta                      |
| GET    | /api/paradas/                    | Listar todas las paradas           |
| GET    | /api/paradas/ruta/{idRuta}       | Paradas de una ruta (por orden) ⭐ |
| POST   | /api/paradas/                    | Crear parada                       |
| PUT    | /api/paradas/                    | Actualizar parada                  |
| DELETE | /api/paradas/{id}                | Eliminar parada                    |

---

## 🧪 Datos de prueba

Al arrancar, el `DataInitializer` carga automáticamente:
- 3 tipos: Fluvial, Terrestre, Aéreo
- 3 países: Colombia, Francia, Japón
- 3 ciudades: Medellín, París, Kioto
- 3 rutas con sus paradas de ejemplo
