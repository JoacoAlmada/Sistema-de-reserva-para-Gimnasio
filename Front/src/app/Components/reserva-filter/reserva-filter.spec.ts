import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservaFilter } from './reserva-filter';

describe('ReservaFilter', () => {
  let component: ReservaFilter;
  let fixture: ComponentFixture<ReservaFilter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReservaFilter]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservaFilter);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
