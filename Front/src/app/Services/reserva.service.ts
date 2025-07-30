import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ReservaDto, ReservaFiltros} from '../Models/reserva.models';
import {Observable} from 'rxjs';
import {ApiConfig} from './config-api';

@Injectable({providedIn: 'root'})
export class ReservaService {
  constructor(private http: HttpClient) {}

  getReservas(filtro: ReservaFiltros): Observable<ReservaDto[]> {
    let params = new HttpParams();
    if (filtro) {
      if (filtro.socio_id) params = params.set('socio_id', filtro.socio_id);
      if (filtro.actividad_id) params = params.set('actividad_id', filtro.actividad_id);
      if (filtro.instructor_id) params = params.set('instructor_id', filtro.instructor_id);
      if (filtro.fechaHora) params = params.set('fechaHora', filtro.fechaHora);
    }
    return this.http.get<ReservaDto[]>(ApiConfig.RESERVA, { params });
  }

  getAllReservas(): Observable<ReservaDto[]> {
    return this.http.get<ReservaDto[]>(ApiConfig.RESERVA);
  }

  altaReserva(datos: {}): Observable<ReservaDto> {
    return this.http.put<ReservaDto>(ApiConfig.RESERVA, datos);
  }

  ProgramarReservas(fecha: string): Observable<any> {
    return this.http.post(ApiConfig.ProgramarReservas(fecha), {});
  }

}
