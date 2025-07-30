import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AltaReserva } from './alta-reserva';

describe('AltaReserva', () => {
  let component: AltaReserva;
  let fixture: ComponentFixture<AltaReserva>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AltaReserva]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AltaReserva);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
