import {Component, OnInit} from '@angular/core';
import {InstructorDto} from '../../Models/instructor.models';
import {ReservaDto, ReservaFiltros} from '../../Models/reserva.models';
import {InstructorService} from '../../Services/instructor.service';
import {ReservaService} from '../../Services/reserva.service';
import {FormsModule} from '@angular/forms';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {AltaReserva} from '../alta-reserva/alta-reserva';

@Component({
  selector: 'app-reserva-disponible',
  imports: [
    FormsModule,
    NgIf,
    NgForOf,
    DatePipe,
    AltaReserva
  ],
  templateUrl: './reserva-disponible.html',
  styleUrl: './reserva-disponible.css'
})
export class ReservaDisponible implements OnInit {

  instructor : InstructorDto[] = [];
  reservaDisponibles: ReservaDto[] = [];
  filtro: ReservaFiltros = {};

  reservaSeleccionado?: ReservaDto;

  constructor(
    private instructorSrv: InstructorService,
    private reservaSrv: ReservaService
  ) {}

  ngOnInit() {
    this.cargarInstructores();
    this.cargarReservaDisponibles();
  }

  cargarInstructores() {
    this.instructorSrv.getInstructores().subscribe(resp => this.instructor = resp);
  }

  cargarReservaDisponibles() {
    this.reservaSrv.getAllReservas().subscribe(resp => {
      this.reservaDisponibles = resp.filter(t => t.status === 'DISPONIBLE');
    });
  }

  filtrarPorInstructor() {
    this.reservaSrv.getAllReservas().subscribe(resp => {
      this.reservaDisponibles = resp.filter(t =>
        t.status === 'DISPONIBLE' &&
        (!this.filtro.instructor_id || t.instructor?.id === + this.filtro.instructor_id)
      );
    });

  }

  seleccionarTurno(r: ReservaDto) {
    this.reservaSeleccionado = r;
  }

  cancelarSeleccion() {
    this.reservaSeleccionado = undefined;
  }

  turnoCreado() {
    this.reservaSeleccionado = undefined;
    this.cargarReservaDisponibles();
  }

}
