import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActividadDto} from '../Models/actividad.models';
import {ApiConfig} from './config-api';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class ActividadService {
  constructor(private http: HttpClient) {}

  getActividades(): Observable<ActividadDto[]> {
    return this.http.get<ActividadDto[]>(ApiConfig.ACTIVIDAD);
  }
}
