import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ReservaService} from '../../Services/reserva.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-programar-reserva',
  imports: [
    FormsModule
  ],
  templateUrl: './programar-reserva.html',
  styleUrl: './programar-reserva.css'
})
export class ProgramarReserva {
  fecha: string = '';
  mensaje: string = '';
  error: string = '';
  cargando = false;

  constructor(private rSrv: ReservaService, private router: Router) { }

  programar() {
    this.mensaje = this.error = '';
    if (!this.fecha) { this.error = "Debe ingresar una fecha"; return; }
    this.cargando = true;
    this.rSrv.ProgramarReservas(this.fecha).subscribe({
      next: _ => {
        this.mensaje = "Reserva programada correctamente!";
        this.cargando = false;
        this.router.navigate(['/treserva-disponibles']);
      },
      error: err => { this.error = err.error?.message || 'Error al programar'; this.cargando = false; }
    });

  }
}
