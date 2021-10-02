import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfViewCourceComponentComponent } from './prof-view-cource-component.component';

describe('ProfViewCourceComponentComponent', () => {
  let component: ProfViewCourceComponentComponent;
  let fixture: ComponentFixture<ProfViewCourceComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfViewCourceComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfViewCourceComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
