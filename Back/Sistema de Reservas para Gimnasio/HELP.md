# 🏋️ Sistema de Reservas para Gimnasio

## 📘 Descripción

Este proyecto es un backend desarrollado en **Java con Spring Boot 3** que permite gestionar las **reservas de clases** de un gimnasio, cumpliendo reglas específicas de negocio.

Permite a los socios:
- Reservar clases.
- Visualizar reservas.
- Programar clases en fechas y horarios determinados.

---

## 🚀 Tecnologías

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (en memoria)
- Jakarta Bean Validation
- ModelMapper (opcional)
- Springdoc OpenAPI / Swagger UI
- Maven

---

## 📦 Estructura del Proyecto

src/
├── main/
│ ├── java/
│ │ └── com/gimnasio/reservas/
│ │ ├── controllers/
│ │ ├── services/
│ │ ├── repositories/
│ │ ├── dtos/
│ │ ├── models/
│ │ └── configs/
│ └── resources/
│ ├── application.properties
│ └── data.sql

---

## 📄 Entidades

### 🧍 Socio

| Campo            | Tipo   | Obligatorio |
|------------------|--------|-------------|
| id               | Long   | No          |
| nombre_completo  | String | Sí          |
| fecha_nacimiento | String | No          |
| numero_socio     | String | Sí          |

### 🧘 Actividad

| Campo            | Tipo     | Obligatorio |
|------------------|----------|-------------|
| id               | Long     | No          |
| nombre_actividad | String   | Sí          |
| descripcion      | String   | No          |
| duracion_minutos | Integer  | Sí          |

### 📅 Reserva

| Campo         | Tipo          | Obligatorio |
|---------------|---------------|-------------|
| id            | Long          | No          |
| socio         | Socio         | Sí          |
| actividad     | Actividad     | Sí          |
| fechaHora     | LocalDateTime | Sí          |
| instructor    | Instructor    | Sí          |
| observaciones | String        | No          |
| status        | String        | No          |

### 🧑‍🏫 Instructor

| Campo          | Tipo   | Obligatorio |
|----------------|--------|-------------|
| id             | Long   | No          |
| nombre_completo| String | Sí          |
| especialidad   | String | Sí          |
| certificacion  | String | No          |

---

## ✅ Reglas de Negocio

- ⛔ Máximo **60 reservas por día** en total.
- ⛔ Cada socio puede tener **máximo 2 reservas por día**.
- ⛔ Un **instructor puede dar hasta 20 clases por día**.
- ⛔ Un instructor **no puede dar dos clases al mismo tiempo**.
- 🕐 Clases entre las **06:00 y 22:00**, cada **45 minutos**.
- ❌ No se permiten reservas fuera de horario.
- 📅 Formato de fecha y hora: `yyyy-MM-dd'T'HH:mm:ss`
- ⏰ Horarios válidos: 06:00, 06:45, 07:30, ..., hasta 21:45.
- 🧑‍🏫 Las clases deben estar **programadas previamente**.

---

## 📡 Endpoints

### 📆 GET `/api/v1/reservas`

**Query Params (solo uno a la vez):**
- `actividad_id`
- `socio_id`
- `instructor_id`
- `fecha_hora` (formato `yyyy-MM-dd'T'HH:mm:ss`)

**Respuesta:**
```json
[
  {
    "id": 100,
    "socio": {
      "id": 1,
      "nombre_completo": "Ana García",
      "numero_socio": "SOC001"
    },
    "actividad": {
      "id": 1,
      "nombre_actividad": "Yoga",
      "duracion_minutos": 45
    },
    "instructor": {
      "id": 1,
      "nombre_completo": "Laura Fernández",
      "especialidad": "Yoga"
    },
    "fechaHora": "2025-08-15T07:30:00",
    "observaciones": "Primera clase del socio"
  }
]
---

✅ PUT /api/v1/reservas
Actualiza una reserva existente.

📝 Request Body:
json
Copiar
Editar
{
  "socio_id": 1,
  "actividad_id": 2,
  "instructor_id": 2,
  "fechaHora": "2025-08-15T09:00:00",
  "observaciones": "Primera clase de CrossFit"
}
🔎 Validaciones:
El socio, instructor y actividad deben existir.

Si alguno no existe → 400 Bad Request

json
Copiar
Editar
{ "mensaje": "Instructor, Socio o Actividad no existen" }
Máximo 2 reservas por socio por día.

Si el socio ya tiene 2 reservas → 400 Bad Request

json
Copiar
Editar
{ "mensaje": "El socio ya tiene el máximo de reservas para el día indicado" }
Un instructor no puede dar más de una clase al mismo tiempo.

Si ya tiene una clase en ese horario → 400 Bad Request

json
Copiar
Editar
{ "mensaje": "Ya existe una clase para el instructor en esa fecha y hora" }
La clase debe estar disponible y programada previamente.

Si no está disponible o no ha sido programada → 400 Bad Request

json
Copiar
Editar
{ "mensaje": "No hay clases disponibles en ese horario para ese instructor" }
➕ POST /api/v1/reservas/programar?fecha=yyyy-MM-dd
Programa todas las clases del día.

🧩 Funcionalidad:
El parámetro fecha debe estar en formato yyyy-MM-dd.

Por cada instructor, se crean clases cada 45 minutos entre 06:00 y 21:45.

Las clases se crean con:

socio = null

actividad = null

observaciones = null

status = "DISPONIBLE"

⚠️ Validaciones:
Si ya hay clases programadas ese día → 400 Bad Request

json
Copiar
Editar
{ "mensaje": "Las clases ya están programadas para la fecha indicada" }
📨 Request:
Sin body.

❗ MANEJO DE ERRORES
Todas las respuestas de error deben:

Ser en formato JSON.

Incluir un código de estado HTTP.

Contener un mensaje claro.

📋 Códigos Esperados:
Código	Descripción
400	Validación fallida
404	Socio, actividad o instructor no encontrado
500	Error inesperado del servidor

🧪 TESTING
Se requiere implementar:

✅ Tests unitarios para los servicios (@Service)

✅ Tests para los controladores (@RestController)
}

