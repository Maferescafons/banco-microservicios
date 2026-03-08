# Sistema Bancario basado en Microservicios

## Descripción

Este proyecto implementa un **Sistema Bancario basado en Microservicios** desarrollado con **Spring Boot**, que permite gestionar clientes, cuentas y movimientos bancarios.

La arquitectura utiliza **comunicación asincrónica mediante RabbitMQ**, permitiendo desacoplar los servicios y mejorar la escalabilidad del sistema.

El sistema está compuesto por dos microservicios principales:

* **cliente-service**: responsable de la gestión de clientes.
* **cuenta-service**: encargado de la administración de cuentas y movimientos.

Cuando un cliente es creado, se publica un evento en **RabbitMQ**, el cual es consumido por el servicio de cuentas para crear automáticamente una cuenta asociada al cliente.

La solución utiliza **Docker y Docker Compose** para la orquestación de servicios y facilitar la ejecución del sistema en cualquier entorno.

---

# Arquitectura

```id="arch001"
cliente-service
      │
      │ Evento: ClienteCreado
      ▼
   RabbitMQ
      │
      ▼
cuenta-service
```

---

# Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* RabbitMQ
* PostgreSQL
* Docker
* Docker Compose
* Maven

---

# Estructura del proyecto

```id="struct001"
proyecto-banco

├── cliente-service
├── cuenta-service
├── docker
│   └── postgres
│       └── init.sql
└── docker-compose.yml
```

---

# Requisitos previos

Antes de ejecutar el proyecto debes tener instalado:

* **Docker**
* **Docker Compose**
* **Git**

Opcional para desarrollo local:

* **Java 17**
* **Maven**

---

# Pasos para levantar el sistema

## 1. Clonar el repositorio

```id="step001"
git clone https://github.com/TU_USUARIO/microservicios-banco.git
```
---
## 2. Levantar el sistema con Docker

Ejecutar:

```id="step002"
docker compose up --build
```

Esto iniciará automáticamente los siguientes servicios:

* PostgreSQL
* RabbitMQ
* cliente-service
* cuenta-service

---

## 3. Verificar que los contenedores estén corriendo

```id="step003"
docker ps
```

Deberías ver algo similar a:

```
cliente-service
cuenta-service
banco-db
banco-rabbit
```

---

# Servicios disponibles

| Servicio        | URL                    |
| --------------- | ---------------------- |
| cliente-service | http://localhost:8080  |
| cuenta-service  | http://localhost:8082  |
| RabbitMQ        | http://localhost:15672 |
| PostgreSQL      | localhost:5432         |

---

# RabbitMQ Management

Acceder al panel de administración:

```id="step004"
http://localhost:15672
```

Credenciales:

```
usuario: guest
password: guest
```

---

# Endpoints principales

## Crear cliente

```id="step005"
POST http://localhost:8080/clientes
```

Ejemplo:

```json id="json001"
{
  "nombre": "Juan Perez",
  "genero": "M",
  "edad": 30,
  "identificacion": "123456789",
  "direccion": "Quito",
  "telefono": "0999999999",
  "password": "1234",
  "estado": true,
  "tipoCuenta": "AHORROS",
  "saldoInicial": 100
}
```

---

## Consultar cuentas

```id="step006"
GET http://localhost:8082/cuentas
```

---

## Registrar movimiento

```id="step07"
POST http://localhost:8082/movimientos
```

---

## Generar reporte de movimientos

```id="step08"
GET http://localhost:8082/reportes?inicio=YYYY-MM-DD&fin=YYYY-MM-DD
```

---

# Características de la solución

* Arquitectura basada en eventos
* Comunicación asincrónica entre microservicios
* Manejo de excepciones personalizadas
* Validaciones de negocio
* Separación de capas (controller, service, repository)
* Uso de DTOs
* Contenerización completa con Docker
* Persistencia con PostgreSQL

---

## Colección Postman

Se incluye la colección de Postman para probar los endpoints del sistema.

Archivo:

postman_collection.json

Importar en Postman para ejecutar las pruebas de la API.
