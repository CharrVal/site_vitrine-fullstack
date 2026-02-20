import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanneauControl } from './panneau-control';

describe('PanneauControl', () => {
  let component: PanneauControl;
  let fixture: ComponentFixture<PanneauControl>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PanneauControl]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PanneauControl);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
