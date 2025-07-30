import {Component, OnInit} from '@angular/core';
import {ReservaDto, ReservaFiltros} from '../../Models/reserva.models';
import {DatePipe} from '@angular/common';
import {ReservaFilter} from '../reserva-filter/reserva-filter';
import {ReservaService} from '../../Services/reserva.service';

@Component({
  selector: 'app-reserva-list',
  imports: [
    DatePipe,
    ReservaFilter
  ],
  templateUrl: './reserva-list.html',
  styleUrl: './reserva-list.css'
})
export class ReservaList implements OnInit {
  Reserva : ReservaDto[] = [];
  filtro : ReservaFiltros = {};
  error: string = '';

  constructor(private rSrv: ReservaService) { }


  ngOnInit(){
    this.cargarReservas({})
  }


  onFiltrar(obj: ReservaFiltros) {
    this.filtro = obj;
    this.cargarReservas(obj);
  }
  cargarReservas (obj: ReservaFiltros) {
    this.rSrv.getReservas(obj).subscribe({
      next: (response) => {
        // Filtra solo turnos con status 'OCUPADO'
        this.Reserva = response.filter(t => t.status?.toLowerCase() === 'ocupado');
        this.error = '';
      },
      error: (err) => {
        this.error = err.error?.message || 'Error al cargar turnos';
        this.Reserva = [];
      }
    });

  }


}
