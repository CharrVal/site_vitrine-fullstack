import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddActualite } from './add-actualite';

describe('AddActualite', () => {
  let component: AddActualite;
  let fixture: ComponentFixture<AddActualite>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddActualite]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddActualite);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
