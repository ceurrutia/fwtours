
# Documento Técnico - Free Walking Tours

## 1. Objetivo

Desarrollar una API RESTful segura que permita:

- Gestión de usuarios con roles
- Gestión de empresas y tours
- Seguridad basada en JWT
- CRUD completo para entidades clave

---

## 2. Modelado de Entidades

### Usuario

- `id`: Long
- `email`: String
- `password`: String (encriptada)
- `rol`: Enum (`CLIENTE`, `EMPRESA`, `ADMIN`)
- `nombreCompleto`: String
- `username`: String

### Empresa

- `id`: Long
- `nombreEmpresa`: String
- `descripcion`: String
- `direccion`: String
- `telefono`: String
- `usuario`: Usuario (OneToOne o ManyToOne)

### Tour

- `id`: Long
- `nombreTour`: String
- `descripcion`: String
- `puntoEncuentro`: String
- `duracion`: String
- `fecha`: LocalDate
- `ciudad`: String
- `pais`: String
- `donacionSugerida`: Double
- `empresa`: Empresa (ManyToOne)

---

## 3. Seguridad

### Filtro JWT personalizado (`JwtAuthFilter`)

- Se ejecuta antes del controlador.
- Extrae y valida el token JWT del header Authorization.
- Si es válido, configura el `SecurityContextHolder`.

### Roles y Autorizaciones

- Uso de `@PreAuthorize("hasRole('EMPRESA')")` para restringir endpoints sensibles.
- Diferenciación de rutas públicas y privadas.

---

## 4. Endpoints REST

### Usuarios

- `POST /api/usuarios/register`
- `POST /api/usuarios/login`
- `GET /api/usuarios` (admin)
- `DELETE /api/usuarios/{id}` (admin)
- `PUT /api/usuarios/{id}`

### Empresas

- `GET /api/empresas`
- `GET /api/empresas/usuario/{usuarioId}`
- `PUT /api/empresas/usuario/{usuarioId}`

### Tours

- `GET /api/empresas/{empresaId}/tours`
- `POST /api/empresas/{empresaId}/tours` solo empresas
- `PUT /api/empresas/{empresaId}/tours/{tourId}`  solo empresas
- `DELETE /api/empresas/{empresaId}/tours/{tourId}`  solo empresas

---

## 5. Futuras mejoras

- Paginación y filtros en listados de tours
- Validaciones con `@Valid` y mensajes claros
- Asignar múltiples fechas por tour
- Subida de imagen para cada tour

---

## 6. Pruebas

Se utilizaron herramientas como:

- Postman (colección de endpoints)
- MySQL Workbench
- JWT Debugger

---

## 7. Base de datos

MySQL o cualquiera compatible con JPA. 

Las relaciones están definidas mediante JPA con `@OneToMany`, `@ManyToOne`.

---

## 8. Autor

- Cecilia Urrutia - Backend Developer
