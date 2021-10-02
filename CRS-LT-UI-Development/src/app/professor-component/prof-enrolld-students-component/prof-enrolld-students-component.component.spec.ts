import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfEnrolldStudentsComponentComponent } from './prof-enrolld-students-component.component';

describe('ProfEnrolldStudentsComponentComponent', () => {
  let component: ProfEnrolldStudentsComponentComponent;
  let fixture: ComponentFixture<ProfEnrolldStudentsComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfEnrolldStudentsComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfEnrolldStudentsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
