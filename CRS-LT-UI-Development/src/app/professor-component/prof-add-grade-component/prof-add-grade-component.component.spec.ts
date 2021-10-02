import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfAddGradeComponentComponent } from './prof-add-grade-component.component';

describe('ProfAddGradeComponentComponent', () => {
  let component: ProfAddGradeComponentComponent;
  let fixture: ComponentFixture<ProfAddGradeComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfAddGradeComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfAddGradeComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
