# 🏋️ SISTEMA DE RESERVAS PARA GIMNASIO - PROYECTO DESDE CERO

# 🧩 ENUNCIADO

## Sistema de Gestión de Reservas para Clases de Gimnasio

Desarrollar un sistema completo de gestión de reservas para un gimnasio con **backend en Java Spring Boot** y **frontend en Angular 19**. El sistema permitirá:

- Reservar clases asociando un socio con un instructor sobre una actividad en una fecha y horario determinado.
- Visualizar reservas realizadas, con posibilidad de aplicar filtros.
- Programar clases para fechas y horarios específicos, respetando reglas de negocio.
- Gestión completa de socios, instructores y actividades.

**💡 IMPORTANTE: Este proyecto se desarrolla DESDE CERO, incluyendo toda la estructura, configuración y funcionalidades.**

---

# 🚀 BACKEND – Java Spring Boot

## Requisitos Generales

- **Spring Boot 3.2+**
- **Java 17+**
- Arquitectura por capas: Entity, DTO, Repository, Service, Controller
- **Spring Data JPA** para persistencia
- **Base de datos H2** en memoria (para desarrollo)
- **Jakarta Bean Validation** (@NotNull, @NotBlank, etc.)
- **Manejo de errores** con @ControllerAdvice y códigos HTTP adecuados
- **CORS** configurado para Angular (puerto 4200)
- **ModelMapper** para mapeo entre entidades y DTOs
- **Tests unitarios** con JUnit 5 y Mockito

---




## 📁 Estructura del Proyecto Backend

src/main/java/com/gimnasio/reservas/
├── GimnasioReservasApplication.java
├── config/
│   ├── CorsConfig.java
│   └── ModelMapperConfig.java
├── entity/
│   ├── Socio.java
│   ├── Actividad.java
│   ├── Instructor.java
│   └── Reserva.java
├── dto/
│   ├── SocioDTO.java
│   ├── ActividadDTO.java
│   ├── InstructorDTO.java
│   ├── ReservaDTO.java
│   ├── ReservaRequestDTO.java
│   └── FiltroReservasDTO.java
├── repository/
│   ├── SocioRepository.java
│   ├── ActividadRepository.java
│   ├── InstructorRepository.java
│   └── ReservaRepository.java
├── service/
│   ├── SocioService.java
│   ├── ActividadService.java
│   ├── InstructorService.java
│   └── ReservaService.java
├── controller/
│   ├── SocioController.java
│   ├── ActividadController.java
│   ├── InstructorController.java
│   └── ReservaController.java
└── exception/
├── GlobalExceptionHandler.java
├── BusinessException.java
└── ResourceNotFoundException.java

src/main/resources/
├── application.yml
├── data.sql
└── schema.sql (opcional)

src/test/java/com/gimnasio/reservas/
├── service/
│   └── ReservaServiceTest.java
└── controller/
└── ReservaControllerTest.java


---

## 📄 Entidades a Crear

### Entidades Requeridas

**Socio.java**
- Campos: id, nombreCompleto, fechaNacimiento, numeroSocio
- Validaciones: @NotBlank en campos obligatorios
- numeroSocio debe ser único

**Actividad.java**
- Campos: id, nombreActividad, descripcion, duracionMinutos
- Validaciones: @NotBlank y @NotNull según corresponda

**Instructor.java**
- Campos: id, nombreCompleto, especialidad, certificacion
- Validaciones: @NotBlank en campos obligatorios

**Reserva.java**
- Campos: id, socio, actividad, fechaHora, instructor, observaciones, status
- Relaciones: @ManyToOne con Socio, Actividad e Instructor
- Status por defecto: "DISPONIBLE"

---

## ⚙️ Configuraciones

### Configuraciones Principales

**application.yml**
- Configurar H2 en memoria
- Habilitar consola H2
- Configurar JPA con create-drop
- Logging para debug

**CorsConfig.java**
- Permitir origen localhost:4200
- Métodos GET, POST, PUT, DELETE, OPTIONS
- Credenciales habilitadas

**ModelMapperConfig.java**
- Bean de ModelMapper para convertir entidades a DTOs

---

## ✅ Reglas de Negocio

- 🏋️ **Máximo 60 reservas por día** para todo el gimnasio
- 👤 **Máximo 2 reservas por socio por día**
- ⏰ **Clases de 45 minutos** entre las 06:00 y las 21:45
- 💪 **Máximo 20 clases por instructor por día**
- 🧑‍🏫 **Un instructor por clase** (no puede dar dos clases simultáneas)
- ❌ **Horario de funcionamiento**: 06:00 a 22:00
- 📅 **Formato de fecha**: yyyy-MM-dd'T'HH:mm:ss
- 🕐 **Horarios válidos**: 06:00, 06:45, 07:30, 08:15, 09:00, 09:45, 10:30, 11:15, 12:00, 12:45, 13:30, 14:15, 15:00, 15:45, 16:30, 17:15, 18:00, 18:45, 19:30, 20:15, 21:00, 21:45

---

## 📦 Datos Iniciales (data.sql)

sql
-- Actividades
INSERT INTO actividades (id, nombre_actividad, descripcion, duracion_minutos)
VALUES (1, 'Yoga', 'Clase de relajación y flexibilidad', 45),
(2, 'CrossFit', 'Entrenamiento funcional de alta intensidad', 45),
(3, 'Spinning', 'Ejercicio cardiovascular en bicicleta estática', 45),
(4, 'Pilates', 'Fortalecimiento del core y postura', 45),
(5, 'Zumba', 'Baile aeróbico divertido', 45);

-- Socios
INSERT INTO socios (id, nombre_completo, fecha_nacimiento, numero_socio)
VALUES (1, 'Ana García', '1985-03-15', 'SOC001'),
(2, 'Carlos Ruiz', '1990-07-22', 'SOC002'),
(3, 'María López', '1988-11-08', 'SOC003'),
(4, 'Diego Morales', '1992-05-14', 'SOC004'),
(5, 'Laura Martín', '1987-09-30', 'SOC005');

-- Instructores  
INSERT INTO instructores (id, nombre_completo, especialidad, certificacion)
VALUES (1, 'Laura Fernández', 'Yoga', 'Instructor Certificado Yoga Alliance'),
(2, 'Roberto Silva', 'CrossFit', 'CrossFit Level 2 Trainer'),
(3, 'Sandra Pérez', 'Spinning', 'Certificación Spinning ACSM'),
(4, 'Miguel Torres', 'Pilates', 'Pilates Method Alliance Certified'),
(5, 'Carmen Vega', 'Zumba', 'Zumba Instructor License');


---

# 📡 ENDPOINTS A IMPLEMENTAR

## 🔍 GET /api/v1/socios
Obtener todos los socios
json
[
{
"id": 1,
"nombreCompleto": "Ana García",
"fechaNacimiento": "1985-03-15",
"numeroSocio": "SOC001"
}
]


## 🔍 GET /api/v1/actividades
Obtener todas las actividades
json
[
{
"id": 1,
"nombreActividad": "Yoga",
"descripcion": "Clase de relajación y flexibilidad",
"duracionMinutos": 45
}
]


## 🔍 GET /api/v1/instructores
Obtener todos los instructores
json
[
{
"id": 1,
"nombreCompleto": "Laura Fernández",
"especialidad": "Yoga",
"certificacion": "Instructor Certificado Yoga Alliance"
}
]


## 📆 GET /api/v1/reservas

**Query params opcionales (solo uno a la vez):**
- actividad_id
- socio_id
- instructor_id
- fecha_hora (formato yyyy-MM-dd'T'HH:mm:ss)
- status (DISPONIBLE, OCUPADA)

json
[
{
"id": 100,
"socio": {
"id": 1,
"nombreCompleto": "Ana García",
"numeroSocio": "SOC001"
},
"actividad": {
"id": 1,
"nombreActividad": "Yoga",
"duracionMinutos": 45
},
"instructor": {
"id": 1,
"nombreCompleto": "Laura Fernández",
"especialidad": "Yoga"
},
"fechaHora": "2025-08-15T07:30:00",
"observaciones": "Primera clase del socio",
"status": "OCUPADA"
}
]


## ✅ PUT /api/v1/reservas

**Validaciones:**
- El socio, instructor y actividad deben existir
- Máximo 2 reservas por socio por día
- Un instructor no puede dar más de una clase al mismo tiempo
- La clase debe estar disponible (status = "DISPONIBLE")
- El horario debe ser válido (06:00-21:45, múltiplos de 45 min)

**Request:**
json
{
"socioId": 1,
"actividadId": 2,
"instructorId": 2,
"fechaHora": "2025-08-15T09:00:00",
"observaciones": "Primera clase de CrossFit"
}


**Response 200:**
json
{
"id": 101,
"socio": { "id": 1, "nombreCompleto": "Ana García", "numeroSocio": "SOC001" },
"actividad": { "id": 2, "nombreActividad": "CrossFit", "duracionMinutos": 45 },
"instructor": { "id": 2, "nombreCompleto": "Roberto Silva", "especialidad": "CrossFit" },
"fechaHora": "2025-08-15T09:00:00",
"observaciones": "Primera clase de CrossFit",
"status": "OCUPADA"
}


## ➕ POST /api/v1/reservas/programar?fecha=yyyy-MM-dd

Programa clases disponibles para todos los instructores en una fecha específica.

**Funcionalidad:**
- Crear clases cada 45 minutos (06:00-21:45) para cada instructor
- Status inicial: "DISPONIBLE"
- Validar que la fecha no esté ya programada

**Response 200:**
json
{
"mensaje": "Clases programadas exitosamente para la fecha 2025-08-15",
"clasesCreadas": 80,
"instructores": 5,
"horarios": 16
}


---

## ❗ Manejo de Errores

### GlobalExceptionHandler.java
Manejo centralizado de errores con:
- BusinessException (400)
- ResourceNotFoundException (404)
- ValidationException (400)
- ResponseEntity con códigos HTTP apropiados

### Mensajes de Error Específicos
- "Instructor, Socio o Actividad no existen"
- "El socio ya tiene el máximo de reservas para el día indicado"
- "Ya existe una clase para el instructor en esa fecha y hora"
- "No hay clases disponibles en ese horario para ese instructor"
- "Las clases ya están programadas para la fecha indicada"
- "Horario fuera del rango permitido (06:00-21:45)"

---