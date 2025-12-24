# DealBumes - API REST de Gestión de Álbumes de Láminas

## Descripción del Proyecto

DealBumes es una API REST desarrollada para la gestión de colecciones de álbumes de láminas. El sistema permite crear, consultar, actualizar y eliminar álbumes, así como gestionar las láminas individuales que los componen. Proporciona funcionalidades avanzadas para identificar láminas faltantes y repetidas dentro de cada álbum.

## Objetivo

Desarrollar un sistema que facilite la administración de colecciones de álbumes de láminas, permitiendo:
- Gestión completa de álbumes (CRUD)
- Administración de láminas dentro de álbumes
- Identificación automática de láminas faltantes y repetidas
- Validación de datos y manejo de errores centralizado

## Características Principales

### Gestión de Álbumes
- ✅ Crear nuevos álbumes con información básica
- ✅ Listar todos los álbumes disponibles
- ✅ Obtener detalles de un álbum específico
- ✅ Actualizar información de álbumes
- ✅ Eliminar álbumes

### Gestión de Láminas
- ✅ Agregar láminas individuales a un álbum
- ✅ Agregar lotes de láminas simultáneamente
- ✅ Rastrear láminas repetidas con cantidad
- ✅ Identificar láminas faltantes
- ✅ Registrar estado de láminas pegadas

## Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|-----------|---------|----------|
| **Java** | 21 | Lenguaje base del proyecto |
| **Spring Boot** | 3.5.8 | Framework principal |
| **Spring Web** | - | Desarrollo de APIs REST |
| **Spring Data MongoDB** | - | Acceso a datos y persistencia |
| **Jakarta Validation** | - | Validación de datos |
| **MongoDB** | - | Base de datos NoSQL |
| **Lombok** | - | Reducción de boilerplate |

## Arquitectura del Sistema

El proyecto utiliza una **arquitectura por capas** que separa responsabilidades:

```
controllers/          → Exposición de endpoints REST
  ├── AlbumController
  └── GlobalExceptionHandler

services/            → Lógica de negocio
  └── AlbumService

repositories/        → Acceso a datos
  └── AlbumRepository

model/              → Entidades del dominio
  ├── Album
  └── Lamina

dto/                → Objetos de transferencia de datos
  └── ApiResponse

exception/          → Excepciones personalizadas
  └── NotFoundException
```

### Patrones Implementados

- **Response Wrapper**: Todas las respuestas se envuelven en `ApiResponse<T>` para consistencia
- **Global Exception Handler**: Manejo centralizado de excepciones
- **Validation Annotations**: Validación declarativa con Jakarta Validation
- **Repository Pattern**: Abstracción de acceso a datos con Spring Data

## Estructura de Datos

### Álbum (Album)
```json
{
  "id": "ObjectId",
  "nombre": "String (requerido)",
  "imagenUrl": "String",
  "fechaLanzamiento": "LocalDate (requerido)",
  "tipoLaminas": "String (requerido)",
  "laminas": [
    {
      "codigo": "String (requerido)",
      "nombre": "String (requerido)",
      "imagenUrl": "String",
      "cantidadRepetidas": "int (≥0)",
      "pegada": "boolean"
    }
  ],
  "createdAt": "Instant (lectura)",
  "updatedAt": "Instant (lectura)"
}
```

## API Endpoints

### Álbumes

| Método | Endpoint | Descripción | Estado HTTP |
|--------|----------|-------------|-------------|
| **POST** | `/api/albumes` | Crear nuevo álbum | 201 Created |
| **GET** | `/api/albumes` | Listar todos los álbumes | 200 OK |
| **GET** | `/api/albumes/{id}` | Obtener álbum por ID | 200 OK |
| **PUT** | `/api/albumes/{id}` | Actualizar álbum | 200 OK |
| **DELETE** | `/api/albumes/{id}` | Eliminar álbum | 200 OK |

### Láminas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **POST** | `/api/albumes/{id}/laminas` | Agregar una lámina |
| **POST** | `/api/albumes/{id}/laminas/lote` | Agregar lote de láminas |
| **GET** | `/api/albumes/{id}/laminas/repetidas` | Listar láminas repetidas |
| **GET** | `/api/albumes/{id}/laminas/faltantes` | Listar láminas faltantes |

## Instalación y Configuración

### Requisitos Previos
- Java 21 instalado
- Maven 3.9.x
- MongoDB ejecutándose localmente (puerto 27017 por defecto)

### Pasos de Instalación

1. **Clonar o descargar el proyecto**
   ```bash
   cd dealbumes
   ```

2. **Configurar MongoDB**
   - Asegurarse de que MongoDB está ejecutándose
   - Verificar conexión en `src/main/resources/application.properties`:
     ```properties
     spring.data.mongodb.host=localhost
     spring.data.mongodb.port=27017
     spring.data.mongodb.database=dealbumes
     ```

3. **Compilar y ejecutar**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   O desde el IDE, ejecutar la clase `DealbumesApplication.java`

4. **Verificar ejecución**
   - La aplicación estará disponible en: `http://localhost:8080`
   - Endpoint de health check: `http://localhost:8080/actuator/health`

## Pruebas

Las funcionalidades se encuentran documentadas en la carpeta `/API` con archivos de prueba:
- **bruno.json**: Colección de endpoints en formato Bruno
- **http---localhost-8080-api-albumes.bru**: Solicitudes HTTP de prueba

### Validaciones Probadas
- ✅ Operaciones CRUD completas
- ✅ Validación de campos requeridos
- ✅ Manejo de errores (404, validaciones)
- ✅ Persistencia en MongoDB
- ✅ Integridad de datos anidados (láminas dentro de álbumes)

## Manejo de Errores

La API utiliza `GlobalExceptionHandler` para proporcionar respuestas consistentes:

```json
{
  "mensaje": "Descripción del error",
  "datos": null
}
```

### Códigos de Estado Principales
- **200 OK**: Operación exitosa
- **201 Created**: Recurso creado exitosamente
- **400 Bad Request**: Validación fallida
- **404 Not Found**: Recurso no encontrado
- **500 Internal Server Error**: Error del servidor

## Convenciones del Proyecto

1. **Nombres**: PascalCase para clases, camelCase para propiedades
2. **Validación**: Jakarta Validation con mensajes en español
3. **Respuestas**: Siempre envueltas en objeto `ApiResponse<T>`
4. **Excepciones**: Heredan de `RuntimeException`, manejadas globalmente
5. **Timestamps**: Automáticos en `createdAt` y `updatedAt`

## Notas Académicas

Este proyecto fue desarrollado con fines académicos, cumpliendo con los requerimientos establecidos en la asignatura **Desarrollo de Software Web II** para el examen final. Demuestra la aplicación de:
- Principios de arquitectura por capas
- Patrones de diseño (Repository, Service, DTO)
- Validación y manejo de errores
- Integración con MongoDB mediante Spring Data
- Desarrollo de APIs REST con Spring Boot

## Estructura de Carpetas

```
dealbumes/
├── src/
│   ├── main/
│   │   ├── java/com/laminas/dealbumes/
│   │   │   ├── DealbumesApplication.java       (punto de entrada)
│   │   │   ├── controller/                     (endpoints REST)
│   │   │   ├── services/                       (lógica de negocio)
│   │   │   ├── repository/                     (acceso a datos)
│   │   │   ├── model/                          (entidades)
│   │   │   ├── dto/                            (objetos de transferencia)
│   │   │   └── exception/                      (excepciones personalizadas)
│   │   └── resources/
│   │       └── application.properties          (configuración)
│   └── test/                                   (tests unitarios)
├── API/                                        (colecciones de prueba)
├── pom.xml                                     (dependencias Maven)
└── README.md                                   (este archivo)
```

**Último actualizado**: Diciembre 2025  
**Versión**: 0.0.1-SNAPSHOT
