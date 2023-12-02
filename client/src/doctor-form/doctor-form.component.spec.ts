import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorFormComponent } from './doctor-form.component';

describe('DoctorFormComponent', () => {
  let component: DoctorFormComponent;
  let fixture: ComponentFixture<DoctorFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoctorFormComponent]
    });
    fixture = TestBed.createComponent(DoctorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
