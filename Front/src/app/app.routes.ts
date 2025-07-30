import { Routes } from '@angular/router';
import {ReservaDisponible} from './Components/reserva-disponible/reserva-disponible';
import {ReservaList} from './Components/reserva-list/reserva-list';
import {ProgramarReserva} from './Components/programar-reserva/programar-reserva';

export const routes: Routes = [
  {path: '', component: ReservaDisponible},
  {path: 'reserva', component: ReservaList},
  {path: 'reserva-disponibles', component: ReservaDisponible},
  {path: 'programar-reserva', component: ProgramarReserva}
];
