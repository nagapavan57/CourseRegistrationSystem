import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorComponentComponent } from './professor-component.component';

describe('ProfessorComponentComponent', () => {
  let component: ProfessorComponentComponent;
  let fixture: ComponentFixture<ProfessorComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
