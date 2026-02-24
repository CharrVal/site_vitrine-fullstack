import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateActualite } from './update-actualite';

describe('UpdateActualite', () => {
  let component: UpdateActualite;
  let fixture: ComponentFixture<UpdateActualite>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateActualite]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateActualite);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
