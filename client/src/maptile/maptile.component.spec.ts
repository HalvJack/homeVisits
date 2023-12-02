import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaptileComponent } from './maptile.component';

describe('MaptileComponent', () => {
  let component: MaptileComponent;
  let fixture: ComponentFixture<MaptileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MaptileComponent]
    });
    fixture = TestBed.createComponent(MaptileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
