import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {ReservaFiltros} from '../../Models/reserva.models';
import {SocioDto} from '../../Models/socio.models';
import {ActividadDto} from '../../Models/actividad.models';
import {InstructorDto} from '../../Models/instructor.models';
import {InstructorService} from '../../Services/instructor.service';
import {SocioService} from '../../Services/socio.service';
import {ActividadService} from '../../Services/actividad.service';

@Component({
  selector: 'app-reserva-filter',
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './reserva-filter.html',
  styleUrl: './reserva-filter.css'
})
export class ReservaFilter {
  @Output() aplicarFiltro = new EventEmitter<ReservaFiltros>();
  tipoFiltro: string = '';
  filtro : ReservaFiltros = {};
  socio : SocioDto[] = [];
  actividad : ActividadDto[] = [];
  instructor : InstructorDto[] = [];


  constructor(
    private socioService: SocioService,
    private actividadService: ActividadService,
    private instructorService : InstructorService
  ) {
    this.socioService.getSocio().subscribe(resp => this.socio = resp);
    this.actividadService.getActividades().subscribe(resp => this.actividad = resp);
    this.instructorService.getInstructores().subscribe(resp => this.instructor = resp);
  }

  limpiarFiltros(excepto: string) {
    if (excepto !== 'socio_id') this.filtro.socio_id = undefined;
    if (excepto !== 'actividad_id') this.filtro.actividad_id = undefined;
    if (excepto !== 'instructor_id') this.filtro.instructor_id = undefined;
    if (excepto !== 'fechaHora') this.filtro.fechaHora = undefined;
  }

  filtrarTurnos() {
    const filtroFinal: ReservaFiltros = {};

    if (this.tipoFiltro === 'socio') {
      if (!this.filtro.socio_id) {
        alert('Debe seleccionar un socio para filtrar.');
        return;
      }
      filtroFinal.socio_id = this.filtro.socio_id;
    }

    if (this.tipoFiltro === 'actividad') {
      if (!this.filtro.actividad_id) {
        alert('Debe seleccionar una actividad para filtrar.');
        return;
      }
      filtroFinal.actividad_id = this.filtro.actividad_id;
    }

    if (this.tipoFiltro === 'instructor') {
      if (!this.filtro.instructor_id) {
        alert('Debe seleccionar un instructor para filtrar.');
        return;
      }
      filtroFinal.instructor_id = this.filtro.instructor_id;
    }

    if (this.tipoFiltro === 'fecha') {
      if (!this.filtro.fechaHora) {
        alert('Debe seleccionar una fecha para filtrar.');
        return;
      }
      // Validar que sea una fecha válida (opcional)
      const fechaValida = /^\d{4}-\d{2}-\d{2}$/.test(this.filtro.fechaHora);
      if (!fechaValida) {
        alert('La fecha ingresada no es válida.');
        return;
      }
      filtroFinal.fechaHora = this.filtro.fechaHora;
    }

    this.aplicarFiltro.emit(filtroFinal);



  }

  onTipoFiltroChange() {
    this.filtro = {};

    switch (this.tipoFiltro) {
      case 'socio':
        this.filtro.socio_id = this.filtro.socio_id;
        break;
      case 'actividad':
        this.filtro.actividad_id = this.filtro.actividad_id;
        break;
      case 'instructor':
        this.filtro.instructor_id = this.filtro.instructor_id;
        break;
      case 'fecha':
        this.filtro.fechaHora = this.filtro.fechaHora;
        break;
    }
  }
}
