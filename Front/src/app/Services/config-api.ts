
export class ApiConfig {
  static readonly API_BASE = 'http://localhost:8080';

  static readonly ACTIVIDAD = `${ApiConfig.API_BASE}/api/v1/actividades`;
  static readonly SOCIO = `${ApiConfig.API_BASE}/api/v1/socios`;
  static readonly INSTRUCTOR = `${ApiConfig.API_BASE}/api/v1/instructores`;
  static readonly RESERVA = `${ApiConfig.API_BASE}/api/v1/reserva`;

  static ProgramarReservas(date: string) {
    return `${ApiConfig.RESERVA}/programar?fecha=${date}`;
  }
}
