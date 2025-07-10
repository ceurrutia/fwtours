# Free Walking Tours

Aplicación web para la gestión de tours guiados a pie. Permite el registro y autenticación de usuarios con roles diferenciados: **cliente**, **empresa** y **administrador**. Las empresas pueden crear, modificar y eliminar sus tours, mientras que los clientes pueden visualizar la oferta de tours disponibles.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security + JWT
- JPA / Hibernate
- MySQL
- Maven
- Postman (para pruebas de endpoints)
- Thymeleaf (si se incluye frontend)

## Funcionalidades principales

### Autenticación y Autorización

- Registro y login de usuarios
- Generación de token JWT al autenticarse
- Control de acceso a rutas por rol (`CLIENTE`, `EMPRESA`, `ADMIN`)

### Empresas

- Crear perfil de empresa
- Editar los datos de su empresa
- Listar todas las empresas (público)
- Obtener empresa por usuario logueado

### Tours

- Listar tours de una empresa
- Crear, modificar y eliminar tours (solo empresas)
- Tours asociados a la empresa logueada
- Visualización pública de tours

## Estructura del proyecto

src
├── controllers
│ └── UsuarioController.java, EmpresaController.java, TourController.java
├── dto
│ └── UsuarioDTO.java, EmpresaDTO.java, TourDTO.java, etc.
├── entities
│ └── Usuario.java, Empresa.java, Tour.java
├── enums
│ └── Rol.java
├── repositories
│ └── UsuarioRepository.java, EmpresaRepository.java, TourRepository.java
├── services
│ └── UsuarioService.java, EmpresaService.java, TourService.java
├── utils
│ └── JwtUtil.java, JwtAuthFilter.java

## Seguridad

- Passwords encriptadas con `PasswordEncoder`
- Acceso protegido a rutas específicas usando `@PreAuthorize`
- Validación de JWT en cada request
- Filtro personalizado `JwtAuthFilter` para autenticación

## Configuración

* Archivo .env:

DB_URL=jdbc:mysql://localhost:3306/tu_base_de_datos
DB_USERNAME=tu_username
DB_PASSWORD=tu_pass
JWT_SECRET=tuJWTSECRET

* En applicationproperties
  
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update

### Desarrollado por Cecilia Urrutia
