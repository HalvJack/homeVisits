import { TestBed } from '@angular/core/testing';

import { MaptileService } from './maptile.service';

describe('MaptileServiceService', () => {
  let service: MaptileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MaptileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
