import {SocioDto} from './socio.models';
import {ActividadDto} from './actividad.models';
import {InstructorDto} from './instructor.models';

export interface ReservaDto {
  id: number;
  socio? : SocioDto;
  actividad?: ActividadDto;
  fechaHora: string;
  instructor?: InstructorDto;
  observaciones : string;
  status? : string;

}

export interface ReservaRequest {
  socio_id : number;
  actividad_id: number;
  instructor_id : number;
  fechaHora: string;
  observaciones : string;

}

export interface ReservaFiltros {
  socio_id?: number;
  actividad_id?: number;
  instructor_id?: number;
  fechaHora?: string; // yyyy-MM-dd'T'HH:mm:ss
}
