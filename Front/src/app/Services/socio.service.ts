import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiConfig} from './config-api';
import {SocioDto} from '../Models/socio.models';

@Injectable({providedIn: 'root'})
export class SocioService {
  constructor(private http: HttpClient) {}

  getSocio(): Observable<SocioDto[]> {
    return this.http.get<SocioDto[]>(ApiConfig.SOCIO);
  }
}
