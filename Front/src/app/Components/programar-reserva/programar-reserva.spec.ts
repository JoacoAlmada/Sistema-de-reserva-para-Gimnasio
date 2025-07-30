import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramarReserva } from './programar-reserva';

describe('ProgramarReserva', () => {
  let component: ProgramarReserva;
  let fixture: ComponentFixture<ProgramarReserva>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProgramarReserva]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProgramarReserva);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
