import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservaDisponible } from './reserva-disponible';

describe('ReservaDisponible', () => {
  let component: ReservaDisponible;
  let fixture: ComponentFixture<ReservaDisponible>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReservaDisponible]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservaDisponible);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
