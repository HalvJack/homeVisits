import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MappositionComponent } from './mapposition.component';

describe('MappositionComponent', () => {
  let component: MappositionComponent;
  let fixture: ComponentFixture<MappositionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MappositionComponent]
    });
    fixture = TestBed.createComponent(MappositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
