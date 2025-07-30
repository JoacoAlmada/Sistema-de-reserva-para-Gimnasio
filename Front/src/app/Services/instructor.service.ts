import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiConfig} from './config-api';
import {InstructorDto} from '../Models/instructor.models';

@Injectable({providedIn: 'root'})
export class InstructorService {
  constructor(private http: HttpClient) {}

  getInstructores(): Observable<InstructorDto[]> {
    return this.http.get<InstructorDto[]>(ApiConfig.INSTRUCTOR);
  }

}
