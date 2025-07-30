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


# 🖥 FRONTEND – Angular 19 + Tailwind CSS

## Requisitos Generales

- **Angular 19**
- **Tailwind CSS 3+** para estilos
- **Arquitectura modular**: Módulos, componentes, servicios separados
- **Reactive Forms** y **Template-driven forms**
- **Observables** para manejo de datos asíncronos
- **Comunicación entre componentes**: @Input, @Output, EventEmitter
- **Routing** con lazy loading
- **HTTP Interceptors** para manejo de errores
- **Responsive design** con Tailwind

---

## 📁 Estructura del Proyecto Frontend

```
src/
├── app/
│   ├── app.component.ts/html/css
│   ├── app.routes.ts
│   ├── app.config.ts
│   ├── core/
│   │   ├── models/
│   │   │   ├── socio.model.ts
│   │   │   ├── actividad.model.ts
│   │   │   ├── instructor.model.ts
│   │   │   ├── reserva.model.ts
│   │   │   └── filtro.model.ts
│   │   ├── services/
│   │   │   ├── socio.service.ts
│   │   │   ├── actividad.service.ts
│   │   │   ├── instructor.service.ts
│   │   │   └── reserva.service.ts
│   │   └── interceptors/
│   │       └── error.interceptor.ts
│   ├── shared/
│   │   ├── components/
│   │   │   ├── navbar/
│   │   │   ├── loading-spinner/
│   │   │   └── error-message/
│   │   └── pipes/
│   │       └── fecha.pipe.ts
│   ├── features/
│   │   ├── reservas/
│   │   │   ├── reservas.routes.ts
│   │   │   ├── components/
│   │   │   │   ├── clases-disponibles/
│   │   │   │   ├── nueva-reserva/
│   │   │   │   ├── listado-reservas/
│   │   │   │   └── filtros-reservas/
│   │   │   └── pages/
│   │   │       ├── clases-disponibles-page/
│   │   │       ├── reservas-page/
│   │   │       └── programar-clases-page/
│   │   ├── socios/
│   │   │   └── ... (similar estructura)
│   │   └── instructores/
│   │       └── ... (similar estructura)
│   └── layout/
│       ├── header/
│       ├── sidebar/
│       └── footer/
├── assets/
│   ├── images/
│   └── styles/
├── environments/
│   ├── environment.ts
│   └── environment.prod.ts
├── styles.css (Tailwind imports)
└── index.html
```

---

## 🎨 Configuración Tailwind

### Configuración Tailwind

**tailwind.config.js**
- Content: archivos HTML y TS
- Tema extendido con colores personalizados
- Configuración responsive

**styles.css**
- Importar directivas Tailwind
- Componentes personalizados (btn-primary, card, form-input)
- Utilidades adicionales

---

### Rutas Angular

**app.routes.ts**
- Ruta principal redirige a /reservas
- Lazy loading para módulos (reservas, socios, instructores)
- Wildcard route para rutas no encontradas

**reservas.routes.ts**
- /reservas → listado de reservas
- /reservas/disponibles → clases disponibles
- /reservas/programar → programar clases

---

## 📝 Funcionalidades Frontend a Implementar

### 1. 🎯 Componente Clases Disponibles

**Responsabilidades:**
- Mostrar lista de clases con status "DISPONIBLE"
- Filtro por instructor (dropdown)
- Seleccionar clase para reservar
- Comunicar con componente "Nueva Reserva" via @Input/@Output
- Navegación condicional (mostrar/ocultar lista vs formulario)
- Redirigir a listado tras guardar exitosamente

**Archivos requeridos:**
- clases-disponibles.component.ts/html/css

### 2. ✅ Componente Nueva Reserva

**Responsabilidades:**
- Formulario template-driven para crear reserva
- Combos dinámicos poblados desde API (socios, actividades, instructores)
- Recibir clase prellenada via @Input
- Validaciones en tiempo real
- PUT request a API
- Emitir eventos @Output al componente padre
- Manejo de errores con mensajes claros

### 3. 📊 Componente Filtros y Grilla de Reservas

**Responsabilidades:**
- Implementar filtrarReservas() - solo un criterio activo
- Implementar cargarReservas(obj: FiltroReservas)
- Tabla responsive de reservas con todas las columnas
- Búsqueda y filtrado en tiempo real

### 4. 📅 Componente Programar Clases

**Responsabilidades:**
- Selector de fecha (DatePicker o input date)
- POST request para crear clases de todos los instructores
- Mostrar resumen de clases creadas
- Validar fechas ya programadas
- Manejo de errores específicos

---

## 🔧 Servicios Angular

## 🔧 Servicios Angular Requeridos

### ReservaService
- obtenerReservas(filtros?) → Observable<Reserva[]>
- crearReserva(reserva) → Observable<Reserva>
- programarClases(fecha) → Observable<any>
- Manejo HttpParams para filtros

### SocioService
- obtenerSocios() → Observable<Socio[]>

### ActividadService
- obtenerActividades() → Observable<Actividad[]>

### InstructorService
- obtenerInstructores() → Observable<Instructor[]>

**Todos deben manejar errores con catchError y mostrar mensajes apropiados**

---

## ✅ Criterios de Evaluación

### Backend (50 puntos)
| Funcionalidad | Puntos |
|---------------|--------|
| Configuración y estructura del proyecto | 5 |
| Entidades JPA con relaciones correctas | 5 |
| Repositorios con queries personalizadas | 5 |
| `GET /api/v1/reservas` con filtros | 10 |
| `PUT /api/v1/reservas` con validaciones | 15 |
| `POST /api/v1/reservas/programar` | 10 |

### Frontend (40 puntos)
## ✅ Criterios de Evaluación

### Backend (60 puntos)
| Funcionalidad | Puntos |
|---------------|--------|
| Configuración proyecto y estructura | 5 |
| Entidades JPA con relaciones | 10 |
| Repositorios con queries específicas | 5 |
| DTOs y mapeo ModelMapper | 5 |
| CRUD básico (GET socios, actividades, instructores) | 5 |
| `GET /api/v1/reservas` con filtros | 10 |
| `PUT /api/v1/reservas` con todas las validaciones | 15 |
| `POST /api/v1/reservas/programar` | 10 |
| Manejo de errores y mensajes específicos | 5 |

### Frontend (40 puntos)
| Componente | Puntos |
|------------|--------|
| **Clases Disponibles** | **15** |
| - Lista solo clases DISPONIBLES | 4 |
| - Filtro por instructor | 4 |
| - Selección y comunicación @Input/@Output | 4 |
| - Navegación condicional lista/formulario | 3 |
| **Nueva Reserva** | **15** |
| - Formulario template-driven | 4 |
| - Combos dinámicos desde API | 4 |
| - Prellenado por @Input | 3 |
| - PUT request y manejo errores | 4 |
| **Filtros y Grilla** | **10** |
| - filtrarReservas() implementado | 5 |
| - cargarReservas(obj) implementado | 5 |

### Testing y Calidad (Bonus +10 puntos)
- Tests unitarios backend (ReservaService, ReservaController)
- Tests frontend (componentes principales)
- Cobertura > 70%

**Total: 100 puntos + 10 bonus**

---



