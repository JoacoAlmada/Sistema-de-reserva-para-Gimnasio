import {Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {ReservaDto} from '../../Models/reserva.models';
import {SocioDto} from '../../Models/socio.models';
import {ActividadDto} from '../../Models/actividad.models';
import {InstructorDto} from '../../Models/instructor.models';
import {SocioService} from '../../Services/socio.service';
import {ActividadService} from '../../Services/actividad.service';
import {InstructorService} from '../../Services/instructor.service';
import {ReservaService} from '../../Services/reserva.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-alta-reserva',
  imports: [
    FormsModule
  ],
  templateUrl: './alta-reserva.html',
  styleUrl: './alta-reserva.css'
})
export class AltaReserva implements OnChanges {
  @Input() reservaSeleccionado?: ReservaDto;
  @Output() AltaReserva = new EventEmitter();
  @Output() Cancelar = new EventEmitter<void>();

  socio : SocioDto[] = [];
  actividades : ActividadDto[] = [];
  instructor : InstructorDto[] = [];

  reserva = {
    socio_id: null as number | null,
    actividad_id: null as number | null,
    instructor_id: null as number | null,
    fechaHora: '',
    observaciones: ''
  }

  constructor(
    private socioSrv: SocioService,
    private actividadSrv: ActividadService,
    private instructorSrv: InstructorService,
    private reservaSrv : ReservaService
  ) {
    this.socioSrv.getSocio().subscribe(resp => this.socio = resp);
    this.actividadSrv.getActividades().subscribe(resp => this.actividades = resp);
    this.instructorSrv.getInstructores().subscribe(resp => this.instructor = resp);
  }


  // Para detectar cambios en turnoSeleccionado y precargar datos en el formulario
  ngOnChanges() {
    if (this.reservaSeleccionado) {
      this.reserva.socio_id = this.reservaSeleccionado.socio?.id ?? null;
      this.reserva.fechaHora = this.reservaSeleccionado.fechaHora ?? '';
      this.reserva.actividad_id = this.reservaSeleccionado.actividad?.id ?? null;
      this.reserva.instructor_id = this.reservaSeleccionado.instructor?.id ?? null;
      this.reserva.observaciones = this.reservaSeleccionado.observaciones ?? '';
    }
  }

  guardarTurno() {
    if (!this.reserva.socio_id || !this.reserva.actividad_id || !this.reserva.instructor_id || !this.reserva.fechaHora) {
      alert("Completar todos los campos requeridos");
      return;
    }

    const dto = {
      socio_id: this.reserva.socio_id,
      actividad_id: this.reserva.actividad_id,
      instructor_id: this.reserva.instructor_id,
      fechaHora: this.reserva.fechaHora,
      observaciones: this.reserva.observaciones
    };

    this.reservaSrv.altaReserva(dto).subscribe(() => {
      alert("Reserva agendado correctamente");
      this.AltaReserva.emit();
    }, err => {
      alert("Error: " + err.error.message);
    });
  }

}
